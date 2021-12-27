package com.game.tictactoe;

import javafx.scene.image.ImageView;
import java.util.*;

public class Move {
    public static char[][] ticTacToeArr = new char[3][3];
    public static List<Integer> occupiedPanesPositionsList = new ArrayList<>();
    public static ImageView img;
    public static Figure userFigure;
    public static List<Integer> computerPossibleChoices;

    public static void userMove(char userChoice, int position) {
        //Add user choice to array
        fillTicTacToeArr(position, userChoice);

        //Add user choice to the list of occupied panes
        addPositionToOccupiedPanesPositionsList(position);

        //Create user figure and add it as children of clicked pane
        userFigure = new Figure(userChoice);
        img = userFigure.createFigure();
        Pane.panesList.get(position).getChildren().add(img);
    }

    public static int computerMove(char computerFigure) {
        //Possible move choices for computer
        computerPossibleChoices = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));

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

        //Create computer figure and add it as children of picked up by computer pane
        Figure compFigure = new Figure(computerFigure);
        img = compFigure.createFigure();
        Pane.panesList.get(computerPosition).getChildren().add(img);

        return computerPosition;
    }

    //Add chosen figure to the array depends on chosen pane position on the list pane
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
        //Print an array on the console
        //arrInfo();
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

    //Winner occurs if in one line (horizontal, vertical or diagonal) there is tree the same figure (circle or cross)
    public static String checkWinner() {
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

            //For cross is a winner
            if (line.equals("xxx")) {
                Board.endRound('x', i);
                System.out.println(3);
                return "x";
            }

            //For circle is a winner
            else if (line.equals("ooo")) {
                Board.endRound('o', i);
                return "o";
            }
        }

        if(Move.occupiedPanesPositionsList.size() == 9) {
            Board.endRound('e', 0);
            return "game over";
        }

        return "continue";
    }
}