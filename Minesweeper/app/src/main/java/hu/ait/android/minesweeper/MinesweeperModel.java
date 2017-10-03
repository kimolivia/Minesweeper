package hu.ait.android.minesweeper;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
import java.util.*;

/**
 * Created by oliviakim on 9/24/17.
 */

public class MinesweeperModel {
    //instance of self in own class

    private static MinesweeperModel minesweeperModel = null;

    //private int numberOfMinesLeft = 3;

    // now to disable anyone from the outside to make another instance of the class by making it private
    private MinesweeperModel() {
        fillMatrix();
        setBombLocations();
        setAllBombNumbers();
    }

    public static MinesweeperModel getInstance() {
        if (minesweeperModel == null) {
            minesweeperModel = new MinesweeperModel();
        }
        return minesweeperModel;
    }

    private Field[][] model = new Field[5][5];

    public void fillMatrix(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Field coordinate = new Field();
                coordinate.setX(i);
                coordinate.setY(j);
                coordinate.setIsAlreadyClicked(false);
                coordinate.setMine(false);
                coordinate.setFlag(false);
                coordinate.setBombNumber(0);
                model[i][j] = coordinate;
            }
        }
    }

    public Field getFieldContent(int i, int j) {
        return model[i][j];
    }

    public ArrayList<Integer> randomIndexGenerator(){
                Random rand = new Random();
                Integer randomResult;
                ArrayList<Integer> randomNumbers = new ArrayList<>();
                for(int i = 0; i < 3; i++){
                    randomResult = rand.nextInt(5);
                    while(randomNumbers.contains(randomResult)){
                        randomResult = rand.nextInt(5);
                    }
                    randomNumbers.add(randomResult);
                }
                return randomNumbers;
            }

    public void setBombLocations(){
        ArrayList<Integer> iCoord = randomIndexGenerator();
        ArrayList<Integer> jCoord = randomIndexGenerator();

        for (int i = 0; i < 3; i++) {
            int x = iCoord.get(i);
            int y = jCoord.get(i);
            model[x][y].setMine(true);
        }
    }

    // checks appropriate surrounding squares to get the total bomb number
    public void assignNumberOfNearbyMines(int i, int j) {
        int newBombNumber = 0;
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if (i + k >= 0 && i + k < 5 && j + l >= 0 && j + l < 5) {
                    if (model[i + k][j + l].isMine()) {
                        newBombNumber++;
                    }
                }
            }
        }
        model[i][j].setBombNumber(newBombNumber);
    }

    // this method sets bomb numbers after setting bomb locations
    public void setAllBombNumbers(){
        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 5 ; j++) {
                if(!model[i][j].isMine()){
                    assignNumberOfNearbyMines(i,j);
                }
            }
        }
    }

    public int getBombNumber(int i, int j) {
        return getFieldContent(i,j).getBombNumber();
    }

    public void resetGame() {
        fillMatrix();
        setBombLocations();
        setAllBombNumbers();

    }

    public boolean isGameOver() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {


            }

        }

        return false;
    }

}





