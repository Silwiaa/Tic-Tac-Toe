package com.game.tictactoe;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Board {

    public GridPane createBoard() {
        GridPane grid = new GridPane();

       for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints(100);
            grid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 3; i++) {
            RowConstraints row = new RowConstraints(100);
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

        Pane.createPanesBorder();
        return grid;
    }

    static void clearAllLists() {
        Figure.playerFigures.clear();
        Move.allMoves.clear();
        Move.userMoves.clear();
        Move.computerMoves.clear();
    }
}
