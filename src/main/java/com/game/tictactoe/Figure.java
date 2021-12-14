package com.game.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Figure {
    private String name;
    private boolean ifIsUserFigure;
    public static List<Figure> playerFigures = new ArrayList<>();
    public static List<Figure> computerFigures = new ArrayList<>();
    private int position;

    public Figure(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public ImageView createFigure(String choosenFigure) {
        if(choosenFigure == "x") {
            Image imageback = new Image("file:src/main/resources/x.png");
            ImageView img = new ImageView(imageback);
            img.setFitHeight(50.0);
            img.setFitWidth(50.0);
            return img;
        } else {
            Image imageback = new Image("file:src/main/resources/zero.png");
            ImageView img = new ImageView(imageback);
            img.setFitHeight(50.0);
            img.setFitWidth(50.0);
            return img;
        }
    }
}
