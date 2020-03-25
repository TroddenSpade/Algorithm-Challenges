package sample;

import javafx.scene.Group;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class MosaicTable {
    private static final int WIDTH = 800;
    private Group group = new Group();
    private int length, tileHeight;
    private int x, y;
    private Tile[][] table;
    private int numberOfMosaics;

    public MosaicTable(int length, int x, int y) {
        this.x = x;
        this.y = y;
        this.length = powerTwo(length);
        this.tileHeight = ((int) Math.floor(WIDTH / this.length));
        if(this.tileHeight > 100)   this.tileHeight=100;
        this.table = new Tile[this.length][this.length];
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.length; j++) {
                table[i][j] = new Tile(i, j, this.tileHeight);
                if (x - 1 == i && y - 1 == j) table[i][j].toCyan();
                group.getChildren().add(table[i][j]);
            }
        }

    }

    private int powerTwo(int length) {
        int result = 1;
        for (int i = 0; i < length; i++) {
            result *= 2;
        }
        return result;
    }

    public void drawLines() {
        Logic logic = new Logic();
        logic.divideAndConquer(0, 0, length - 1, length - 1, x - 1, y - 1);
        ArrayList<Tile> top = logic.getTopList();
        ArrayList<Tile> left = logic.getLeftList();
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.length; j++) {
                if (!top.contains(table[i][j])) {
                    group.getChildren().add(new Line(table[i][j].getX(), table[i][j].getY(), table[i][j].getX() + tileHeight, table[i][j].getY()));
                }
                if (!left.contains(table[i][j])) {
                    group.getChildren().add(new Line(table[i][j].getX(), table[i][j].getY(), table[i][j].getX(), table[i][j].getY() + tileHeight));
                }
            }
        }
        group.getChildren().add(new Line(0, tileHeight * this.length, tileHeight * this.length, tileHeight * this.length));
        group.getChildren().add(new Line(tileHeight * this.length, 0, tileHeight * this.length, tileHeight * this.length));
        numberOfMosaics = top.size();
    }

    public Group getGroup() {
        return group;
    }

    public int getNumberOfMosaics() {
        return numberOfMosaics;
    }
}
