package com.game.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Figure {
    private char chosenFigure;
    private Image cross = new Image("file:src/main/resources/x.png");
    private Image circle = new Image("file:src/main/resources/zero.png");
    private ImageView img;
    public static Image finishCross = new Image("file:src/main/resources/finish.png");
    public static Image finishCircle = new Image("file:src/main/resources/finishzero.png");
    public static ImageView finishImg;

    public Figure(char chosenFigure) {
        this.chosenFigure = chosenFigure;
    }

    public Character getChoisenFigure() {
        return chosenFigure;
    }

    //Returns figure image view depending on chosen figure
    public ImageView createFigure() {
        if(getChoisenFigure() == 'x') {
            img = new ImageView(cross);
            setFormattingForImg(img);
            return img;

        } else {
            img = new ImageView(circle);
            setFormattingForImg(img);
            return img;
        }
    }

    //Changes figure in winning line
    public static ImageView createFinishFigure(char figure) {
        if(figure == 'x') {
            finishImg = new ImageView(finishCross);
            setFormattingForImg(finishImg);
            return finishImg;

        } else {
            finishImg = new ImageView(finishCircle);
            setFormattingForImg(finishImg);
            return finishImg;
        }
    }

    public static void setFormattingForImg(ImageView img) {
        img.setFitHeight(50.0);
        img.setFitWidth(50.0);
    }
}
