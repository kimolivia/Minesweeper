package hu.ait.android.minesweeper;

/**
 * Created by oliviakim on 9/26/17.
 */

public class Field {

    private int x;
    private int y;
    private int bombNumber;
    private boolean alreadyClicked;
    private boolean isMine;
    private boolean isFlag;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBombNumber() {
        return bombNumber;
    }

    public void setBombNumber(int bombNumber) {
        this.bombNumber = bombNumber;
    }

    public boolean isAlreadyClicked() {
        return alreadyClicked;
    }

    public void setIsAlreadyClicked(boolean hasUserClickHere) {
        this.alreadyClicked = hasUserClickHere;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

}
