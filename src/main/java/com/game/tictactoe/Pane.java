package com.game.tictactoe;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;

import java.util.*;

public class Pane extends StackPane {
    public static List<Pane> panesList = new ArrayList<>();
    int panelNumber;

    String userChoise;
    String chosenFigure;
    ImageView img;
    private boolean ifIsOccupated;

    public Pane(int panelNumber) {
        this.panelNumber = panelNumber;
        onClickPane();
    }

    public int getPanelNumber() {
        return panelNumber;
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
        setOnMouseClicked(event -> {
            //Checks if user has chosen his figure. If not, default figure for user is "o"
            if(event.getButton() == MouseButton.PRIMARY) {
                if (Choose.userChoseList.size() > 0) {
                    userChoise = Choose.userChoseList.get(0).getUserChoose();
                } else {
                    userChoise = "o";
                }

                int position = getPanelNumber();
                System.out.println("User choise" + position);
                Figure figure = new Figure(userChoise, position);
                Figure.playerFigures.add(figure);

                Move userMove = new Move(position);
                Move.userMoves.add(userMove);
                Move.allMoves.add(userMove);

                chosenFigure = figure.getName();
                img = figure.createFigure(chosenFigure);
                getChildren().add(img);

                String computerFigure = "";
                if(chosenFigure == "x") {
                    computerFigure = "o";
                } else {
                    computerFigure = "x";
                }
                Move.computerMove(computerFigure);
            }
        });
    }
}
