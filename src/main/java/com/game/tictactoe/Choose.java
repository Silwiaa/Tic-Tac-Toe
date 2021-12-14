package com.game.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Choose {

    private String userChoise;
    public static List<Choose> userChoseList = new ArrayList<>();

    public Choose(String userChoise) {
        this.userChoise = userChoise;
    }

    public String getUserChoose() {
        return userChoise;
    }
}
