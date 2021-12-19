package com.game.tictactoe;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javax.print.DocFlavor;

public class Bet {

    public static void acceptBet() {
        Alert betAlert = new Alert(Alert.AlertType.CONFIRMATION, "Current bet is 50 credits - do you accept the challenge?", ButtonType.YES, ButtonType.NO);
        betAlert.setTitle("Make your bet before start the game");
        betAlert.showAndWait();

        if (betAlert.getResult() == ButtonType.YES) {
            System.out.println("confirmed");
            changePlayersCredits();
        }
        else {
            System.out.println("canceled");
            makeUserBet();
        }
    }

    public static void changePlayersCredits() {
        int playerCredits = Integer.parseInt(Board.playerCreditsValue.getText());
        int computerCredits = Integer.parseInt(Board.computerCreditsValue.getText());
        int bet = 50;

        playerCredits -= bet;
        computerCredits -=bet;

        Board.computerCreditsValue.setText(Integer.toString(computerCredits));
        Board.playerCreditsValue.setText(Integer.toString(playerCredits));
    }

    public static void makeUserBet() {

        Board.playerBetValue.setDisable(false);
        Board.playerBetValue.setOnKeyReleased(event -> {
            int playerCredits = Integer.parseInt(Board.playerCreditsValue.getText());
            int computerCredits = Integer.parseInt(Board.computerCreditsValue.getText());
            String playerBet = Board.playerBetValue.getText();

            try {
                int playerBetValueInt = Integer.parseInt(playerBet);
                Board.confirmBetBtn.setDisable(false);

            } catch (NumberFormatException e) {
                Alert wrongValueAlert = new Alert(Alert.AlertType.ERROR);
                wrongValueAlert.setTitle("Wrong bet value");
                wrongValueAlert.setContentText("Make numeric bet");
                wrongValueAlert.show();
            }
            Board.computerCreditsValue.setText(Integer.toString(computerCredits));
            Board.playerCreditsValue.setText(Integer.toString(playerCredits));
        });
    }
}
