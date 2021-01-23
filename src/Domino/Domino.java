package Domino;

public class Domino {
    int leftValue;
    int rightValue;
    boolean freeRight = true;
    boolean freeLeft = true;

    public Domino(int leftValue, int rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    @Override
    public String toString() {
        return "| " + leftValue + "--" + rightValue + " |" + "\n";
    }
}
