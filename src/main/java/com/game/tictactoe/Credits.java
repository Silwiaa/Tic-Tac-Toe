package com.game.tictactoe;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Credits {


    public static int startCreditsValue;
    public static int currentComputerCredits;
    public static int currentPlayerCredits;
    public static int creditsOnTable;
    public static int maxBet = 0;


    public static void setStartCreditsValue() {
        startCreditsValue = 1000;
        Board.computerCreditsValue.setText(Integer.toString(startCreditsValue));
        Board.playerCreditsValue.setText(Integer.toString(startCreditsValue));
        Board.playerBetValue.clear();
    }

    public static void makeNewBet(int betValue) {
        checkMaxPossibleBet();
        if (betValue <= maxBet) {
            acceptNewBet(betValue);
            Board.grid.setDisable(true);
            if (Board.playerBetValue.isDisabled()) {
                Board.grid.setDisable(false);
            }
        } else {
            changeBetValue();
        }
    }

    public static void checkMaxPossibleBet() {
        currentComputerCredits = Integer.parseInt(Board.computerCreditsValue.getText());
        currentPlayerCredits = Integer.parseInt(Board.playerCreditsValue.getText());

        if (currentComputerCredits > currentPlayerCredits) {
            maxBet = currentPlayerCredits;
        } else {
            maxBet = currentComputerCredits;
        }
    }

    public static void acceptNewBet(int betValue) {

        if (Board.playerBetValue.getText().isEmpty()) {
            String betAlertText = "Current bet is " + betValue + " credits - do you accept the challenge?";
            Alert betAlert = new Alert(Alert.AlertType.CONFIRMATION, betAlertText, ButtonType.YES, ButtonType.NO);
            betAlert.setTitle("Accepting bet");
            betAlert.showAndWait();

            if (betAlert.getResult() == ButtonType.YES) {
                Board.startLbl.setText("Play again:");
                Board.restartBtn.setDisable(false);
                decreaseCredits(betValue);
            } else {
                Board.playerBet.setText("Make your bet: ");
                Board.startLbl.setText("");
                Board.playerBetValue.setDisable(false);
                makeUsrBet();
            }
        } else {
            decreaseCredits(betValue);
        }
    }

    public static void changeBetValue() {
        Alert changeBet = new Alert(Alert.AlertType.ERROR);
        changeBet.setTitle("Change bet value");
        changeBet.setContentText("Maximal possible bet value is " + maxBet);
        changeBet.show();
        Board.playerBetValue.clear();
        makeUsrBet();
    }

    public static void makeUsrBet() {
        Board.playerBetValue.setOnKeyReleased(event -> {
            String playerBet = Board.playerBetValue.getText();
            try {
                Integer.parseInt(playerBet);
                Board.confirmBetBtn.setDisable(false);

            } catch (NumberFormatException e) {
                Alert wrongValueAlert = new Alert(Alert.AlertType.ERROR);
                wrongValueAlert.setTitle("Wrong bet value");
                wrongValueAlert.setContentText("Make numeric bet");
                wrongValueAlert.show();
            }
        });
    }

    public static void decreaseCredits(int betValue) {
        System.out.println("decreasePlayersCredits");
        creditsOnTable = betValue * 2;
        setCreditsValue();

        Board.computerCreditsValue.setText(Integer.toString(currentComputerCredits - betValue));
        Board.playerCreditsValue.setText(Integer.toString(currentPlayerCredits - betValue));
        Board.playerBetValue.setText(Integer.toString(creditsOnTable));
        Board.playerBet.setText("Credits on table:");

        Board.confirmBetBtn.setDisable(true);
        Board.playerBetValue.setDisable(true);

        setCreditsValue();
    }

    public static void setCreditsValue() {
        currentComputerCredits = Integer.parseInt(Board.computerCreditsValue.getText());
        currentPlayerCredits = Integer.parseInt(Board.playerCreditsValue.getText());
    }

    public static void addWinnerCredits(char winner) {
        boolean userWins = false;
        char userFigure = Choice.userChoiceList.get(0);
        if (winner == userFigure) {
            userWins = true;
        }
        increaseWinnerCredits(userWins);
    }

    public static void increaseWinnerCredits(boolean userWins) {
        if(userWins) {
            Board.playerCreditsValue.setText(Integer.toString(currentPlayerCredits + creditsOnTable));
        } else {
            Board.computerCreditsValue.setText(Integer.toString(currentComputerCredits + creditsOnTable));
        }
        setCreditsValue();
        Board.playerBetValue.clear();
    }
}
