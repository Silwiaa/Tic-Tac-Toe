package com.game.tictactoe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Board {

    private BorderPane root = new BorderPane();

    private GridPane rightGrid = new GridPane();
    private GridPane topGrid = new GridPane();
    public static GridPane grid = new GridPane();
    private GridPane bottomGrid = new GridPane();

    private ColumnConstraints column = new ColumnConstraints(100);
    private RowConstraints row = new RowConstraints(100);

    public static TextField playerBetValue = new TextField();
    public static TextField computerCreditsValue = new TextField();
    public static TextField playerCreditsValue = new TextField();

    public static Button confirmBetBtn = new Button();
    public static Button xButton = new Button();
    public static Button oButton = new Button();
    public static Button restartBtn = new Button();
    public static Button continueBtn = new Button();

    public static Label computerCredits = new Label("Computer credits:");
    public static Label playerCredits = new Label("Player credits:");
    public static Label playerBet = new Label("");
    private Label chooseLbl = new Label("Choose your figure:");
    public static Label startLbl = new Label("Start the game by click on the board or choose your figure:");

    private Image imageback = new Image("file:src/main/resources/tlo.jpg");
    private BackgroundSize backgroundSize = new BackgroundSize(
            910, 610, true, true, true, true);
    private BackgroundImage backgroundImage = new BackgroundImage(
            imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    private Background background = new Background(backgroundImage);

    public BorderPane createBoard() {

        //Set font and other formatting for created grids
        formattingGrid(rightGrid);
        formattingGrid(topGrid);
        formattingGrid(grid);
        formattingGrid(bottomGrid);

        ////Add children to rightGrid
        rightGrid.setVgap(10.0);
        rightGrid.setHgap(10.0);

        xButton.setText("    X    ");
        oButton.setText("    O    ");
        formattingGridLabels(startLbl);
        formattingGridLabels(chooseLbl);

        rightGrid.add(chooseLbl, 0, 0, 10, 1);
        rightGrid.add(xButton, 5, 1, 2, 1);
        rightGrid.add(oButton, 5, 2, 2, 1);

        //Add children to topGrid
        topGrid.setVgap(10.0);
        topGrid.setHgap(10.0);
        formattingGridLabels(computerCredits);
        formattingGridLabels(playerCredits);
        formattingGridLabels(playerBet);

        confirmBetBtn.setText("CONFIRM BET");

        playerCreditsValue.setDisable(true);
        computerCreditsValue.setDisable(true);
        playerBetValue.setDisable(true);
        confirmBetBtn.setDisable(true);

        topGrid.add(computerCredits, 0, 0, 4, 1);
        topGrid.add(computerCreditsValue, 5, 0, 2, 1);

        topGrid.add(playerCredits, 0, 1, 4, 1);
        topGrid.add(playerCreditsValue, 5, 1, 2, 1);

        topGrid.add(playerBet, 0, 2, 4, 1);
        topGrid.add(playerBetValue, 5, 2, 2, 1);
        topGrid.add(confirmBetBtn, 8, 2, 4, 1);

        Credits.setStartCreditsValue();

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

        continueBtn.setText("  CONTINUE  ");
        continueBtn.setDisable(true);

        bottomGrid.setVgap(10.0);
        bottomGrid.setHgap(10.0);

        bottomGrid.add(startLbl, 0, 1, 10, 1);
        bottomGrid.add(restartBtn, 11, 1, 4, 1);
        bottomGrid.add(continueBtn, 16, 1, 4, 1);

        //Set on action buttons events
        onClickFiguresButtons(oButton);
        onClickFiguresButtons(xButton);
        onClickRestartButton(restartBtn);
        onClickConfirmBetBtn(confirmBetBtn);
        onClickContinueButton(continueBtn);

        //Add grids for the main Border Pane
        root.setTop(topGrid);
        root.setCenter(grid);
        root.setBottom(bottomGrid);
        root.setRight(rightGrid);

        return root;
    }

   private void formattingGrid(GridPane gridToDecorate) {
        gridToDecorate.setBackground(new Background(
                new BackgroundFill(Color.web("#446C9D"), new CornerRadii(0), new Insets(0))));
        gridToDecorate.setPadding(new Insets(30));
        gridToDecorate.setAlignment(Pos.CENTER);

        if (gridToDecorate == grid) {
           grid.setBackground(background);
        }
   }

   private void formattingGridLabels(Label label) {
       label.setTextFill(Color.web("#FFF"));
       label.setFont(new Font("Roboto", 18));
       label.setPadding(new Insets(2));
   }

    private void onClickFiguresButtons(Button button) {
        button.setOnAction(event -> {
            clearData();
            Choice.userChoiceList.clear();
            setDisableForButtons();
            startLbl.setText("");

            if (button.equals(oButton)) {
                Credits.makeNewBet(50);
                Choice.userChoiceList.add('o');
                Move.computerMove('x');
            } else {
                Choice.userChoiceList.add('x');
            }
        });
    }

    private void onClickRestartButton(Button button) {
        button.setOnAction(event -> {
            restartGame();
        });
    }

    public static void restartGame() {
        Credits.setStartCreditsValue();
        playerBet.setText("");
        xButton.setDisable(false);
        oButton.setDisable(false);
        restartBtn.setDisable(true);
        continueBtn.setDisable(true);
        startLbl.setText("Start the game by click on the board or choose your figure:");
        clearData();
    }

    public static void onClickContinueButton(Button button) {
        button.setOnAction(event -> {
            clearData();
            setDisableForButtons();
            continueBtn.setDisable(true);
            startLbl.setText("Play again:");
            char userFigure = Pane.checkUserFigureChoice();
            if (userFigure == 'o') {
                if(Board.playerBetValue.getText().isEmpty()) {
                    Credits.makeNewBet(50);
                    Move.computerMove('x');
                } else {
                    Move.computerMove('x');
                }
            }
        });
    }

    private void onClickConfirmBetBtn(Button button) {
        button.setOnAction(event -> {
            int betValue = Integer.parseInt(playerBetValue.getText());
            Credits.makeNewBet(betValue);
            startLbl.setText("Play again:");
            restartBtn.setDisable(false);
        });
    }

    public static void setDisableForButtons() {
        oButton.setDisable(true);
        xButton.setDisable(true);
    }

   public static void clearData() {
        Move.ticTacToeArr = new char[3][3];
        Move.occupiedPanesPositionsList.clear();
        for (Pane pane : Pane.panesList) {
           pane.getChildren().clear();
       }
   }

    public static void endRound(char whoHasWon, int row) {
        Alert gameStageAlert = new Alert(Alert.AlertType.INFORMATION);
        gameStageAlert.setTitle("End of the round");
        String winner = "";

        if(whoHasWon =='e') {
            gameStageAlert.setContentText("Round over - nobody wins");
            continueBtn.setDisable(false);
            Credits.maxBet = Integer.parseInt(Board.playerBetValue.getText());
            startLbl.setText("Play again to start with new credits value or continue");
        } else {
            if (whoHasWon == 'x') {
                winner = "Cross";
                Board.printWinningLineOnBoard(row, 'x');
                Credits.addWinnerCredits('x');
            } else {
                winner = "Circle";
                Board.printWinningLineOnBoard(row, 'o');
                Credits.addWinnerCredits('o');
            }

            int currentComputerCredits = Integer.parseInt(computerCreditsValue.getText());
            int currentPlayerCredits = Integer.parseInt(playerCreditsValue.getText());

            if (currentComputerCredits > 0 && currentPlayerCredits > 0) {
                startLbl.setText("Play again to start with new credits or continue");
                gameStageAlert.setContentText(winner + " has won this round");
                continueBtn.setDisable(false);
            } else {
                continueBtn.setDisable(true);
                String looser = "";
                if (currentComputerCredits > currentPlayerCredits) {
                    looser =  "User";
                    winner = "computer";
                } else {
                    looser =  "Computer";
                    winner = "user";
                }
                gameStageAlert.setContentText(looser + " lost all credits, " + winner + " wins the game");
            }
        }
        gameStageAlert.show();
        restartBtn.setDisable(false);
    }

    public static void printWinningLineOnBoard(int position, char figure) {
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