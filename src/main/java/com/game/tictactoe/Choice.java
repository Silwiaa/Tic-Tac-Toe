package com.game.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Choice {
    private char userChoice;
    public static List<Character> userChoiceList = new ArrayList<>();

    public Choice(char userChoise) {
        this.userChoice = userChoise;
        addToChoiceList();
    }

    public Character getUserChoice() {
        return userChoice;
    }

    //Add user choice to choices list if user clicks xButton or oButton
    public void addToChoiceList() {
        userChoiceList.add(getUserChoice());
    }
}
