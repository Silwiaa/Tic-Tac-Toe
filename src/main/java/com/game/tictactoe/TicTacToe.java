package com.game.tictactoe;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe extends Application {

    private Button startBtn = new Button();
    private Button xButton = new Button();
    private Button oButton = new Button();
    private Label startLbl = new Label("Start game or choose your figure");
    private Label chooseLbl = new Label("Choose your figure");
    private Image imageback = new Image("file:src/main/resources/tlo.jpg");
    BorderPane root = new BorderPane();
    GridPane topGrid = new GridPane();
    GridPane bottomGrid = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Formating top Grid Pane
        topGrid.setBackground(new Background(new BackgroundFill(Color.web("#446C9D"), new CornerRadii(0), new Insets(0))));
        startBtn.setText("  RESTART  ");
        startLbl.setTextFill(Color.web("#FFF"));
        startLbl.setFont(new Font("Roboto", 18));
        startLbl.setPadding(new Insets(2));
        topGrid.add(startLbl, 0, 0, 4, 1);
        topGrid.add(startBtn, 5, 0, 2, 1);
        topGrid.setVgap(10.0);
        topGrid.setHgap(10.0);
        topGrid.setPadding(new Insets(30));
        topGrid.setAlignment(Pos.CENTER);

        startBtn.setOnAction(event -> {
            Choose.userChoseList.clear();
            for (int i = 0 ; i < Pane.panesList.size() ; i++) {
                Pane.panesList.get(i).getChildren().clear();
            }
            Board.clearAllLists();
        });

        //Formating center Grid Pane
        Board board = new Board();
        GridPane grid = board.createBoard();
        BackgroundSize backgroundSize = new BackgroundSize(910, 610, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(background);

        //Formating bottom Grid Pane
        bottomGrid.setBackground(new Background(new BackgroundFill(Color.web("#446C9D"), new CornerRadii(0), new Insets(0))));
        xButton.setText("  X  ");
        oButton.setText("  O  ");
        chooseLbl.setTextFill(Color.web("#FFF"));
        chooseLbl.setFont(new Font("Roboto", 18));
        chooseLbl.setPadding(new Insets(2));
        bottomGrid.add(xButton, 0, 0, 4, 1);
        bottomGrid.add(chooseLbl, 5, 0, 8, 1);
        bottomGrid.add(oButton, 14, 0, 4, 1);
        bottomGrid.setVgap(10.0);
        bottomGrid.setHgap(10.0);
        bottomGrid.setPadding(new Insets(30));
        bottomGrid.setAlignment(Pos.CENTER);

        //Choose player figure
        xButton.setOnAction(event -> {
            chooseLbl.setText("You are playing X");
            Choose.userChoseList.clear();
            Choose userChose = new Choose("x");
            Choose.userChoseList.add(userChose);

            for (int i = 0 ; i < Pane.panesList.size() ; i++) {
                Pane.panesList.get(i).getChildren().clear();
            }

            Board.clearAllLists();
        });

        oButton.setOnAction(event -> {
            chooseLbl.setText("You are playing O");
            Choose.userChoseList.clear();
            Choose userChose = new Choose("o");
            Choose.userChoseList.add(userChose);

            for (int i = 0 ; i < Pane.panesList.size() ; i++) {
                Pane.panesList.get(i).getChildren().clear();
            }

            Board.clearAllLists();
        });

        root.setTop(topGrid);
        root.setCenter(grid);
        root.setBottom(bottomGrid);

        Scene scene = new Scene(root, 910, 610);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


