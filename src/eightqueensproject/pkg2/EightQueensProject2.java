/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eightqueensproject.pkg2;

import java.util.*;

/**
 *
 * @author armel
 */
public class EightQueensProject2 {
    

    int[][] map = new int[8][8];
    int[][] testMap = new int[8][8];
    int heuristicValue = 0;
    int queen = 0;
    int reset = 0;
    int next = 0;
    boolean recentMap = true;
    int close = 8;
    
  public static void main(String[] args) {
        EightQueensProject2 one = new EightQueensProject2();
        one.randomizeSelect();
        one.advancement();

    }
    EightQueensProject2() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                map[i][j] = 0;

            }

        }
    }

    public void randomizeSelect() {

        Random rand = new Random();
        int number;
        while (queen < 8) {
            for (int i = 0; i < 8; i++) {
                map[rand.nextInt(7)][i] = 1;
                queen++;
            }

        }
        heuristicValue = heuristicVal(map);
    }

    // Heuristic
    public boolean RowConflicts(int[][] test, int a) { //determines row conflicts
        boolean conFound = false;
        int count = 0;

        for (int i = 0; i < 8; i++) {
            if (test[i][a] == 1) {
                count++;
            }
        }
        if (count > 1) {
            conFound = true;
        }
        return conFound;
    }

    public boolean diagonalCon(int[][] test, int a, int b) {//determines diagonal conflicts
        boolean digConflict = false;

        for (int i = 1; i < 8; i++) {
            if (digConflict) {
                break;
            }

            if ((a + i < 8) && (b + i < 8)) {
                if (test[a + i][b + i] == 1) {
                    digConflict = true;
                }
            }
            if ((a - i >= 0) && (b - i >= 0)) {
                if (test[a - i][b - i] == 1) {
                    digConflict = true;
                }
            }
            if ((a + i < 8) && (b - i >= 0)) {
                if (test[a + i][b - i] == 1) {
                    digConflict = true;
                }
            }
            if ((a - i >= 0) && (b + i < 8)) {
                if (test[a - i][b + i] == 1) {
                    digConflict = true;
                }
            }
        }
        return digConflict;
    }

    public boolean ColConflict(int[][] test, int j) { //determines column conflicts
        boolean colFound = false;
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (test[j][i] == 1) {
                count++;
            }
        }
        if (count > 1) {
            colFound = true;
        }
        return colFound;
    }

    public int heuristicVal(int[][] test) {//Counts the number of queens in conflict
        int count = 0;
        boolean countCol;
        boolean countRow;
        boolean countDig;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (test[i][j] == 1) {
                    countCol = ColConflict(test, j);
                    countRow = RowConflicts(test, i);
                    countDig = diagonalCon(test, i, j);

                    if (countCol || countRow || countDig) {
                        count++;
                    }
                }
            }
        }
        return count;

    }

    //Huristic 2
    public void advancement() { // Tracks queen movement
        int[][] queenArray = new int[8][8];
        int colTotal;
        int lowColumn;
        int lowRow;
        int oldQueen = 0;
        recentMap = false;

        while (true) {
            colTotal = 0;

            for (int i = 0; i < 8; i++) {
                System.arraycopy(map[i], 0, testMap[i], 0, 8);
            }
            while (colTotal < 8) {
                for (int i = 0; i < 8; i++) {
                    testMap[i][colTotal] = 0;
                }
                for (int i = 0; i < 8; i++) {
                    if (map[i][colTotal] == 1) {
                        oldQueen = i;
                    }
                    testMap[i][colTotal] = 1;
                    queenArray[i][colTotal] = heuristicVal(testMap);
                    testMap[i][colTotal] = 0;
                }
                testMap[oldQueen][colTotal] = 1;
                colTotal++;
            }

            if (startOver(queenArray)) {
                queen = 0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        map[i][j] = 0;
                    }
                }
                randomizeSelect();
                System.out.println("RESTART");
                reset++;
            }
            
            lowColumn = searchLowCol(queenArray);
            lowRow = searchLowRow(queenArray);
            
             for (int i = 0; i < 8; i++) {
                map[i][lowColumn] = 0;
            }

            map[lowRow][lowColumn] = 1;
            next++;
            heuristicValue = heuristicVal(map);

            if (heuristicVal(map) == 0) {
                System.out.println("\nCurrent State");
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        System.out.print(map[i][j] + " ");
                    }
                    System.out.print("\n");
                }
                System.out.println("Solution Found!");
                System.out.println("State changes: " + next);
                System.out.println("Restarts: " + reset);
                break;
            }

            System.out.println("\n");
            System.out.println("Current h: " + heuristicValue);
            System.out.println("Current State");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.print("\n");
            }
            System.out.println("Neighbors found with lower h: " + close);
            System.out.println("Setting new current State");
        
    

        }
    }

    public boolean startOver(int[][] test) {
        int smallValue = 8;
        boolean again = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (test[i][j] < smallValue) {
                    smallValue = test[i][j];
                }
            }

        }
        if(close == 0) {
            
            again = true;

        }
        return again;
    }
    
     public int searchLowCol(int[][] test) { //finds column of minimum 
        int lowColumn = 8;
        int lowTotal = 8;
        int total = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (test[i][j] <lowTotal) {
                    lowTotal = test[i][j];
                    lowColumn= j;
                }
                if (test[i][j] < heuristicValue) {
                    total++;
                }
            }
        }
        close = total;
        return lowColumn;
    
    }
     public int searchLowRow(int[][] test) { //finds row of minimum 
        int lowRow = 8;
        int lowTotal = 8;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (test[i][j] < lowTotal) {
                    lowTotal= test[i][j];
                    lowRow = i;
                }
            }
        }
        return lowRow;
    }
    
     

}