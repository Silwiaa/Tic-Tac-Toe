package com.game.tictactoe;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;

import java.util.*;

public class Pane extends StackPane {
    public static List<Pane> panesList = new ArrayList<>();
    private int paneNumber;
    private MouseButton mouseButton;
    private char userChoise;
    private char computerFigure;
    private int position;
    private ImageView img;

    public Pane(int paneNumber) {
        this.paneNumber = paneNumber;
        onClickPane();
    }

    public int getPanelNumber() {
        return paneNumber;
    }

    public static void createPanesBorder() {
        panesList.get(0).setStyle("-fx-border-color: #446C9D;" + "-fx-border-width: 0 2 0 0;");
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
        //When pane clicked checks user figure. Default user figure is cross.
        //On clicking xButton or oButton - user figure is changed depends on clicked button.
        //Cross starts.
        setOnMouseClicked(event -> {
            mouseButton = event.getButton();
            if (mouseButton == MouseButton.PRIMARY) {
                //Checks if xButton or oButton have been chosen
                if (Choice.userChoiceList.size() > 0) {
                    userChoise = Choice.userChoiceList.get(0);
                } else {
                    userChoise = 'x';
                }

                if (userChoise == 'x') {
                    computerFigure = 'o';
                } else {
                    computerFigure = 'x';
                }
                //Check position of clicked pane
                position = getPanelNumber();

                //Check if user picked up not taken pane
                boolean checkIfIsFree = checkIfPaneIsOccupied(position);

                if (checkIfIsFree) {
                    //If picked up pane is free - make user move
                    Move.userMove(userChoise, position);

                    //Create user figure and add it as children of clicked pane
                    Figure userFigure = new Figure(userChoise);
                    img = userFigure.createFigure();
                    getChildren().add(img);
                } else {
                    return;
                }

                //Check if after user move game can be continued. If check winner is c - game can be continued.
                char checkWinner = Move.checkWinner();

                //Check if computer can make a move (if total moves is less than 9 and there is no winner - computer moves)
                if (Move.occupiedPanesPositionsList.size() < 9 && checkWinner == 'c') {
                    int computerChoise = Move.computerMove(computerFigure);
                    Figure computerfigure = new Figure(computerFigure);
                    img = computerfigure.createFigure();
                    Pane.panesList.get(computerChoise).getChildren().add(img);

                    checkWinner = Move.checkWinner();

                    //Check if computer move was the last of possible moves. If yes - and there is no winner - game over
                    if(checkWinner == 'c' && Move.occupiedPanesPositionsList.size() == 9) {
                        Board.endGame(checkWinner);
                    }
                } else {
                    Board.endGame(checkWinner);
                }

                //After user and computer moves check again if there is winner. If yes - end game and show winner
                checkWinner = Move.checkWinner();
                if(checkWinner != 'c') {
                    Board.endGame(checkWinner);
                }
            }
        });
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
