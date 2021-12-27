package com.game.tictactoe;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    BorderPane root = new BorderPane();

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Create board and root for scene
        Board board = new Board();
        root = board.createBoard();

        Scene scene = new Scene(root, 910, 610);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


