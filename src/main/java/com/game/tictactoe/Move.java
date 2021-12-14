package com.game.tictactoe;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Move {
    public static List<Move> userMoves = new ArrayList<>();
    public static List<Move> computerMoves = new ArrayList<>();
    public static List<Move> allMoves = new ArrayList<>();
    public static ImageView img;
    int position;

    public Move(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position = position;
    }

    public static void computerMove(String computerFigure) {
        List<Integer> computerPosibleChoises = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        List<Integer> prohibenMoves = new ArrayList<>();

        for (Move move : allMoves) {
           int prohibenMove =  move.getPosition();
           prohibenMoves.add(prohibenMove);
        }
        System.out.println("Posible moves" + computerPosibleChoises);
        System.out.println("Prohiben moves" + prohibenMoves);

        computerPosibleChoises.removeAll(prohibenMoves);
        System.out.println("Rest moves" + computerPosibleChoises);
        System.out.println(computerPosibleChoises.size());

        int i = computerPosibleChoises.size()-1;
        int position = computerPosibleChoises.get(i);

        Figure figure = new Figure(computerFigure, position);
        Figure.computerFigures.add(figure);

        Move computerMove = new Move(position);
        Move.computerMoves.add(computerMove);
        Move.allMoves.add(computerMove);

        img = figure.createFigure(computerFigure);
        Pane.panesList.get(position).getChildren().add(img);
    }
}
