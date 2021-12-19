package com.game.tictactoe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Board {

    private BorderPane root = new BorderPane();
    private GridPane rightGrid = new GridPane();
    private GridPane topGrid = new GridPane();
    private GridPane grid = new GridPane();
    private GridPane bottomGrid = new GridPane();

    private Image imageback = new Image("file:src/main/resources/tlo.jpg");
    private BackgroundSize backgroundSize = new BackgroundSize(910, 610, true, true, true, true);
    private BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    private Background background = new Background(backgroundImage);

    private ColumnConstraints column = new ColumnConstraints(100);
    private RowConstraints row = new RowConstraints(100);

    private Label computerCredits = new Label("Computer credits:");
    private Label playerCredits = new Label("Player credits:");
    private Label playerBet = new Label("Choose your bet value:");
    public static Button confirmBetBtn = new Button();
    public static TextField playerBetValue = new TextField();
    public static TextField computerCreditsValue = new TextField();
    public static TextField playerCreditsValue = new TextField();

    private Button xButton = new Button();
    private Button oButton = new Button();
    private Label chooseLbl = new Label("Choose your figure:");
    public static Label startLbl = new Label("Start the game by click on the board or choose your figure:");
    public static Button restartBtn = new Button();

    public BorderPane createBoard() {

        //Set font and other formatting for created grids
        formatingGrid(rightGrid);
        formatingGrid(topGrid);
        formatingGrid(grid);
        formatingGrid(bottomGrid);

        ////Add children to rightGrid
        rightGrid.setVgap(10.0);
        rightGrid.setHgap(10.0);

        xButton.setText("    X    ");
        oButton.setText("    O    ");
        formatingGridLabels(startLbl);
        formatingGridLabels(chooseLbl);

        rightGrid.add(chooseLbl, 0, 0, 10, 1);
        rightGrid.add(xButton, 5, 1, 2, 1);
        rightGrid.add(oButton, 5, 2, 2, 1);

        //Add children to topGrid
        topGrid.setVgap(10.0);
        topGrid.setHgap(10.0);
        formatingGridLabels(computerCredits);
        formatingGridLabels(playerCredits);
        formatingGridLabels(playerBet);

        playerCreditsValue.setText("1000");
        playerCreditsValue.setDisable(true);
        computerCreditsValue.setText("1000");
        computerCreditsValue.setDisable(true);
        playerBetValue.setDisable(true);
        confirmBetBtn.setText("CONFIRM BET");
        confirmBetBtn.setDisable(true);

        topGrid.add(computerCredits, 0, 0, 4, 1);
        topGrid.add(computerCreditsValue, 5, 0, 2, 1);

        topGrid.add(playerCredits, 0, 1, 4, 1);
        topGrid.add(playerCreditsValue, 5, 1, 2, 1);

        topGrid.add(playerBet, 0, 2, 4, 1);
        topGrid.add(playerBetValue, 5, 2, 2, 1);
        topGrid.add(confirmBetBtn, 8, 2, 4, 1);

        //Add children to middle grid
        for (int i = 0; i < 3; i++) {
            grid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 3; i++) {
            grid.getRowConstraints().add(row);
        }

        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Pane pane = new Pane(count);
                Pane.panesList.add(pane);
                grid.add(pane, i, j);
                count++;
            }
        }
        //Create border for children of middle grid
        Pane.createPanesBorder();

        //Add children to bottomGrid
        restartBtn.setText("  RESTART  ");
        restartBtn.setDisable(true);

        bottomGrid.setVgap(10.0);
        bottomGrid.setHgap(10.0);

        bottomGrid.add(startLbl, 0, 1, 10, 1);
        bottomGrid.add(restartBtn, 11, 1, 4, 1);

        //Set on anction buttons events
        onClickButtonsEvent(oButton);
        onClickButtonsEvent(xButton);
        onClickButtonsEvent(restartBtn);
        onClickButtonsEvent(confirmBetBtn);

        //Add grids for the main Border Pane
        root.setTop(topGrid);
        root.setCenter(grid);
        root.setBottom(bottomGrid);
        root.setRight(rightGrid);

        return root;
    }


   private void formatingGrid(GridPane gridToDecorate) {
        gridToDecorate.setBackground(new Background(new BackgroundFill(Color.web("#446C9D"), new CornerRadii(0), new Insets(0))));
        gridToDecorate.setPadding(new Insets(30));
        gridToDecorate.setAlignment(Pos.CENTER);

        if (gridToDecorate == grid) {
           grid.setBackground(background);
        }
   }

   private void formatingGridLabels(Label label) {
       label.setTextFill(Color.web("#FFF"));
       label.setFont(new Font("Roboto", 18));
       label.setPadding(new Insets(2));
   }

   private void onClickButtonsEvent(Button button) {
        button.setOnAction(event -> {
            if(button != confirmBetBtn) {
                clearData();
                if (button == oButton) {
                    Choice.userChoiceList.clear();
                    Choice.userChoiceList.add('o');
                    try {
                        Move.checkIfIsUserTurn('o');
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Choice.userChoiceList.clear();
                    Choice.userChoiceList.add('x');
                    restartBtn.setDisable(true);
                    startLbl.setText("Start the game by click on the board or choose your figure");
                }

            } else {
                int playerCredits = Integer.parseInt(Board.playerCreditsValue.getText());
                int computerCredits = Integer.parseInt(Board.computerCreditsValue.getText());
                int playerBetValueInt = Integer.parseInt(Board.playerBetValue.getText());
                playerCredits -= playerBetValueInt;
                computerCredits -= playerBetValueInt;
                Board.computerCreditsValue.setText(Integer.toString(computerCredits));
                Board.playerCreditsValue.setText(Integer.toString(playerCredits));
                Board.confirmBetBtn.setDisable(true);
                Board.playerBetValue.setDisable(true);
            }
        });
   }

   public void clearData() {
        Move.ticTacToeArr = new char[3][3];
        Move.occupiedPanesPositionsList.clear();
        for (Pane pane : Pane.panesList) {
           pane.getChildren().clear();
       }
   }

    public static void endGame(char whoHasWon) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End of the game");
        String winner = "";
        if(whoHasWon !='c') {
            if (whoHasWon == 'x') {
                winner = "Cross";
            } else {
                winner = "Circle";
            }
            alert.setContentText(winner + " has won the game");
        } else {
            alert.setContentText("Game over - nobody wins");
        }
        alert.show();
        restartBtn.setDisable(false);
        startLbl.setText("Restart or choose your figure to play again");
    }

    public static void printResultOnBoard(int position, char figure) {
        ImageView img1;
        ImageView img2;
        ImageView img3;
        img1 = Figure.createFinishFigure(figure);
        img2 = Figure.createFinishFigure(figure);
        img3 = Figure.createFinishFigure(figure);

        int x = 0;
        int y = 0;
        int z = 0;

        switch (position) {
            case 0:
                x = 0;
                y = 3;
                z = 6;
                break;
            case 1:
                x = 1;
                y = 4;
                z = 7;
                break;
            case 2:
                x = 2;
                y = 5;
                z = 8;
                break;
            case 3:
                x = 0;
                y = 1;
                z = 2;
                break;
            case 4:
                x = 3;
                y = 4;
                z = 5;
                break;
            case 5:
                x = 6;
                y = 7;
                z = 8;
                break;
            case 6:
                x = 0;
                y = 4;
                z = 8;
                break;
            case 7:
                x = 6;
                y = 4;
                z = 2;
                break;
        }
        Pane.panesList.get(x).getChildren().clear();
        Pane.panesList.get(x).getChildren().add(img1);

        Pane.panesList.get(y).getChildren().clear();
        Pane.panesList.get(y).getChildren().add(img2);

        Pane.panesList.get(z).getChildren().clear();
        Pane.panesList.get(z).getChildren().add(img3);
    }
}
