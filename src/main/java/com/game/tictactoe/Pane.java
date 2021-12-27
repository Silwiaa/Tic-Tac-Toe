package com.game.tictactoe;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import java.util.*;

public class Pane extends StackPane {
    private final int paneNumber;
    private MouseButton mouseButton;
    private int position;
    private boolean checkIfIsFree;
    public static char userChoice;
    private boolean isUserTurn;
    public static List<Pane> panesList = new ArrayList<>();
    public static char computerFigure;

    public Pane(int paneNumber) {
        this.paneNumber = paneNumber;
        onClickPane();
    }

    public int getPanelNumber() {
        return paneNumber;
    }

    public static void createPanesBorder() {
        panesList.get(0).setStyle("-fx-border-color: #446C9D;-fx-border-width: 0 2 0 0;");
        panesList.get(1).setStyle("-fx-border-color: #446C9D;" + "-fx-border-width: 2 2 0 0;");
        panesList.get(2).setStyle("-fx-border-color: #446C9D;" + "-fx-border-width: 2 2 0 0;");
        panesList.get(3).setStyle("-fx-border-color: #446C9D;" + "-fx-border-width: 0 2 0 0;");
        panesList.get(4).setStyle("-fx-border-color: #446C9D;" + "-fx-border-width: 2 2 0 0;");
        panesList.get(5).setStyle("-fx-border-color: #446C9D;" + "-fx-border-width: 2 2 0 0;");
        panesList.get(6).setStyle("-fx-border-color: #446C9D;" + "-fx-border-width: 0 0 0 0;");
        panesList.get(7).setStyle("-fx-border-color: #446C9D;" + "-fx-border-width: 2 0 0 0;");
        panesList.get(8).setStyle("-fx-border-color: #446C9D;" + "-fx-border-width: 2 0 0 0;");
    }

    public void onClickPane() {
        setOnMouseClicked(event -> {
            mouseButton = event.getButton();
            if (mouseButton == MouseButton.PRIMARY) {

                //Make bet on start the game
                if (Move.occupiedPanesPositionsList.size() == 0) {
                    Board.setDisableForButtons();
                    Credits.makeNewBet(50);
                }

                //Check position of clicked pane
                position = getPanelNumber();
                isUserTurn = true;

                //Check user figure. Cross starts
                userChoice = checkUserFigureChoice();
                if (userChoice != 'x' && Move.occupiedPanesPositionsList.size() == 0) {
                    isUserTurn = false;
                }

                //Check if user picked up not taken pane
                checkIfIsFree = checkIfPaneIsOccupied(position);

                if (checkIfIsFree && isUserTurn) {
                    //If picked up pane is free - make user move
                    Move.userMove(userChoice, position);

                } else {
                    return;
                }

                //Check if after user move there is a winner.
                String checkWinner = Move.checkWinner();

                //Check if computer can make a move (if total moves is less than 9 and there is no winner - computer moves)
                if (checkWinner.equals("continue")) {
                    Move.computerMove(computerFigure);

                    //Check if after computer move there is a winner.
                    Move.checkWinner();
                }
            }
        });
    }

    public static char checkUserFigureChoice() {
        //Checks user figure choice. If not chosen - cross is default.
        if (Choice.userChoiceList.size() > 0) {
            userChoice = Choice.userChoiceList.get(0);
            computerFigure = 'x';
        } else {
            userChoice = 'x';
            Choice.userChoiceList.add(userChoice);
        }
        if (userChoice == 'x') {
            computerFigure = 'o';
        }
        return userChoice;
    }

    public boolean checkIfPaneIsOccupied(int passedPosition) {

        //Checks if user haven't used the same pane twice. If yes - show alert
        for (int i : Move.occupiedPanesPositionsList) {
            if(i == passedPosition) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid move");
                alert.setContentText("Choose another move");
                alert.show();
                return false;
            }
        }
        return true;
    }
}
