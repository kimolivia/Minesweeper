package hu.ait.android.minesweeper;

import java.util.ArrayList;
import java.util.Random;
import java.util.*;

/**
 * Created by oliviakim on 9/24/17.
 */

public class MinesweeperModel {
    //instance of self in own class
    private static MinesweeperModel minesweeperModel = null;

    // now to disable anyone from the outside to make another instance of the class by making it private
    private MinesweeperModel() {
    }

    public static MinesweeperModel getInstance() {
        if (minesweeperModel == null) {
            minesweeperModel = new MinesweeperModel();
        }
        return minesweeperModel;
    }

    public static final int EMPTY = 0;
    public static final int FLAG = 1;
    public static final int BOMB = 2;

    private Field[][] model;

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
    public void assignNumberOfNearbyMines(int i, int j){
        int newBombNumber;
        for (int k = i - 1; k <= i + 1 ; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                // only if not a mine and does not equal the square we are checking
                if(model[k][l].isMine() && (k != i && l != j)){
                    // only checks if the indices are within the right bounds
                    if((0 <= k) && (k <= 4) && (0 <= j) && (j <= 4)){
                        newBombNumber = model[i][j].getBombNumber() + 1;
                        model[i][j].setBombNumber(newBombNumber);
                    }
                }
            }
        }
    }

    // this method sets bomb numbers after setting bomb locations
    public void setAllBombNumbers(){
        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 5 ; j++) {
                // only sets bomb number if it is not a bomb itself
                if(!model[i][j].isMine()){
                    assignNumberOfNearbyMines(i,j);
                }
            }
        }
    }



}

