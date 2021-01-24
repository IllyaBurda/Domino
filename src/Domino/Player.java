package Domino;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    List<Domino> myDominos = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", myDominos=" + myDominos +
                '}';
    }

    public void nextStep(GameController controller) {
        int[] currentValue = controller.getCurrentValue();
        boolean flag = false;
        Domino nextDomino = null;
        for (Domino domino : myDominos) {
            if (domino.leftValue == currentValue[0] || domino.rightValue == currentValue[0] || domino.leftValue == currentValue[1] || domino.rightValue == currentValue[1]) {
                nextDomino = domino;
                flag = true;
            }
        }

        if (flag == false) {
            controller.changePlayer();
        } else {
            nextStep(nextDomino, controller);
        }
    }



    public void calculateSumDominos() {
        int sumOfDomino = 0;
        for (Domino domino : myDominos) {
            sumOfDomino += domino.leftValue + domino.rightValue;
        }
        System.out.println(this.myDominos.size()+" -- "+this.name + " - " + sumOfDomino);
    }

    public void nextStep(Domino domino, GameController gameController) {
        int[] currentValue = null;
        myDominos.remove(domino);
        if (gameController.table.isEmpty()) {
            gameController.table.add(domino);
        } else {
            currentValue=gameController.getCurrentValue();
if(domino.leftValue==currentValue[0]  ){
    domino.freeLeft=false;
    gameController.table.addFirst(domino);
}else if(domino.rightValue==currentValue[0]){
    domino.freeRight=false;
    gameController.table.addFirst(domino);
}else if(domino.leftValue==currentValue[1]){
    domino.freeLeft=false;
    gameController.table.addLast(domino);
}else{
    domino.freeRight=false;
    gameController.table.addLast(domino);
}
        }

        gameController.changePlayer();
    }
}
