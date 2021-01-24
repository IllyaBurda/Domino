package Domino;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.*;

public class GameController {
    List<Domino> market = new ArrayList<>();
    List<Player> players = new ArrayList<Player>();
    int currentPlayer;
    Deque<Domino> table = new ArrayDeque<Domino>();

    public GameController(Player... players) {
        for (Player temp : players) {
            this.players.add(temp);
        }
        start();
    }

    public static void main(String[] args) {

        Player playerOne = new Player("David");
        Player playerTwo = new Player("John");
        Player playerThree = new Player("Billi");
        Player playerFour = new Player("Edvard");
        GameController controller = new GameController(playerOne, playerTwo, playerThree, playerFour);
        while (controller.isEnded() == false) {
            controller.players.get(controller.currentPlayer).nextStep(controller);
        }
    }

    private boolean isEnded() {
        boolean result = false;
        for (Player player : players) {
            if (player.myDominos.isEmpty()) {
                System.out.println("Game over");
                result = true;

            }
        }
        System.out.println(table+" "+currentPlayer);
        for (Player player : players) {
            player.calculateSumDominos();
        }
        return result;
    }

    private void start() {
        initSet();
        dealOfDomino();
        firstStep();
    }

    private void firstStep() {
        Domino currentDomino = null;
        Player currentPlayer = null;
        boolean flag = false;
        for (Player player : players) {
            for (Domino domino : player.myDominos) {
                if (domino.leftValue == 1 && domino.rightValue == 1) {
                    currentDomino = domino;
                    currentPlayer = player;
                }
            }
        }
        currentPlayer.nextStep(currentDomino, this);
    }

    private void dealOfDomino() {
        Collections.shuffle(market);
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.myDominos.add(market.get(0));
                market.remove(0);
            }
        }
    }

    private void initSet() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Domino domino = new Domino(i, j);
                if (!findDoubles(i, j)) {
                    market.add(domino);
                }
            }
        }
    }

    private boolean findDoubles(int i, int j) {
        boolean result = false;
        for (Domino domino : market) {
            if (Math.max(i, j) == Math.max(domino.leftValue, domino.rightValue) && Math.min(i, j) == Math.min(domino.leftValue, domino.rightValue)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void changePlayer() {
        if (currentPlayer == players.size() - 1) {
            this.currentPlayer = 0;
        } else {
            this.currentPlayer++;
        }
    }

    public int[] getCurrentValue() {
        int[] result = new int[2];
        Domino left = this.table.getFirst();
        Domino right = this.table.getLast();
        if(left==right){
            result[0] = left.leftValue;
            result[1] = right.rightValue;
        }else{
            result[0] = left.getFreeValue();
            result[1] = right.getFreeValue();
        }

        return result;
    }


}
