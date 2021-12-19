package com.game.tictactoe;

import javafx.scene.image.ImageView;
import java.util.*;

public class Move {
    public static char[][] ticTacToeArr = new char[3][3];
    public static List<Integer> occupiedPanesPositionsList = new ArrayList<>();

    public static void checkIfIsUserTurn(char userChoise) throws InterruptedException {
        //If user had been chosen to start with circle figure - computer will start the game
        if(userChoise == 'x') {
            return;

        } else {
            int computerChoice = Move.computerMove('x');
            Figure computerfigure = new Figure('x');
            ImageView img = computerfigure.createFigure();
            Thread.sleep(500);
            Pane.panesList.get(computerChoice).getChildren().add(img);
            return;
        }
    }

    public static void userMove(char userChoise, int position) {
        //Add user choice to array
        fillTicTacToeArr(position, userChoise);

        //Add user choice to the list of occupied panes
        addPositionToOccupiedPanesPositionsList(position);
    }

    public static int computerMove(char computerFigure) {
        //Possible move choices for computer
        List<Integer> computerPossibleChoices = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));

        //Remove taken positions from possible choices of move
        computerPossibleChoices.removeAll(occupiedPanesPositionsList);

        //Computer pick up random position from possible move choices
        Random rand = new Random();
        int x = rand.nextInt(computerPossibleChoices.size());
        int computerPosition = computerPossibleChoices.get(x);

        //Add computer choice to array
        fillTicTacToeArr(computerPosition, computerFigure);

        //Add computer choice to the list of occupied panes
        addPositionToOccupiedPanesPositionsList(computerPosition);

        return computerPosition;
    }

    //Add chosen figure to the array depends on chosen pane position
    public static void fillTicTacToeArr(int pasedPosition, char figure) {
        if (pasedPosition == 0) {
            ticTacToeArr[0][0] = figure;
        }
        if (pasedPosition == 1) {
            ticTacToeArr[1][0] = figure;
        }
        if (pasedPosition == 2) {
            ticTacToeArr[2][0] = figure;
        }
        if (pasedPosition == 3) {
            ticTacToeArr[0][1] = figure;
        }
        if (pasedPosition == 4) {
            ticTacToeArr[1][1] = figure;
        }
        if (pasedPosition == 5) {
            ticTacToeArr[2][1] = figure;
        }
        if (pasedPosition == 6) {
            ticTacToeArr[0][2] = figure;
        }
        if (pasedPosition == 7) {
            ticTacToeArr[1][2] = figure;
        }
        if (pasedPosition == 8) {
            ticTacToeArr[2][2] = figure;
        }
        arrInfo();
    }

    //Print array in console
    public static void arrInfo() {
        for (int i = 0; i < ticTacToeArr.length ; i++) {
            for (int j = 0; j <ticTacToeArr[i].length ; j++)
                System.out.print(ticTacToeArr[i][j]+"");
            System.out.println();
        }
    }

    public static void addPositionToOccupiedPanesPositionsList(int passedPosition) {
        occupiedPanesPositionsList.add(passedPosition);
    }

    //Winner occurs if in one line (horizontal, vertical or diagonal) there is the same figure (circle or cross)
    public static char checkWinner() {
        for (int i = 0; i < 8; i++) {
            String line = null;

            switch (i) {
                case 0:
                    line = "" + ticTacToeArr[0][0] + ticTacToeArr[0][1] + ticTacToeArr[0][2];
                    break;
                case 1:
                    line = "" + ticTacToeArr[1][0] + ticTacToeArr[1][1] + ticTacToeArr[1][2];
                    break;
                case 2:
                    line = "" + ticTacToeArr[2][0] + ticTacToeArr[2][1] + ticTacToeArr[2][2];
                    break;
                case 3:
                    line = "" + ticTacToeArr[0][0] + ticTacToeArr[1][0] + ticTacToeArr[2][0];
                    break;
                case 4:
                    line = "" + ticTacToeArr[0][1] + ticTacToeArr[1][1] + ticTacToeArr[2][1];
                    break;
                case 5:
                    line = "" + ticTacToeArr[0][2] + ticTacToeArr[1][2] + ticTacToeArr[2][2];
                    break;
                case 6:
                    line = "" + ticTacToeArr[0][0] + ticTacToeArr[1][1] + ticTacToeArr[2][2];
                    break;
                case 7:
                    line = "" + ticTacToeArr[0][2] + ticTacToeArr[1][1] + ticTacToeArr[2][0];
                    break;
            }
            //For X winner
            if (line.equals("xxx")) {
                System.out.println("Cross won");
                Board.printResultOnBoard(i, 'x');
                return 'x';
            }

            // For O winner
            else if (line.equals("ooo")) {
                System.out.println("Circle won");
                System.out.println("Line number: " + i);
                Board.printResultOnBoard(i, 'o');
                return 'o';
            }
        }
        return 'c';
    }
}
