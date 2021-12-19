package com.game.tictactoe;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Figure {
    private char choisenFigure;
    private Image cross = new Image("file:src/main/resources/x.png");
    private Image circle = new Image("file:src/main/resources/zero.png");
    private ImageView img;
    public static Image finishCross = new Image("file:src/main/resources/finish.png");
    public static Image finishCircle = new Image("file:src/main/resources/finishzero.png");
    public static ImageView finishImg;

    public Figure(char choisenFigure) {
        this.choisenFigure = choisenFigure;
    }

    public Character getChoisenFigure() {
        return choisenFigure;
    }

    //Changes figure in winning line
    public static ImageView createFinishFigure(char figure) {
        if(figure == 'x') {
            finishImg = new ImageView(finishCross);
            finishImg.setFitHeight(50.0);
            finishImg.setFitWidth(50.0);
            return finishImg;
        } else {
            finishImg = new ImageView(finishCircle);
            finishImg.setFitHeight(50.0);
            finishImg.setFitWidth(50.0);
            return finishImg;
        }
    }

    //Returns proper figure image depending on chosen figure
    public ImageView createFigure() {
        if(getChoisenFigure() == 'x') {
            img = new ImageView(cross);
            img.setFitHeight(50.0);
            img.setFitWidth(50.0);
            return img;
        } else {
            img = new ImageView(circle);
            img.setFitHeight(50.0);
            img.setFitWidth(50.0);
            return img;
        }
    }
}
