package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.shape.Line;

import java.awt.*;
import java.util.ArrayList;


public class Table {
    private static final double WIDTH = 800;
    private Group group = new Group();
    private int tileHeight;
    private int x, y;
    private Rectangle[][] table;

    public Table(int x, int y) {
        this.x = x;
        this.y = y;
        this.tileHeight = ((int) Math.floor(WIDTH / Math.max(x, y)));
        if (this.tileHeight > 100) this.tileHeight = 100;
        this.table = new Rectangle[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                table[i][j] = new Rectangle(i * this.tileHeight, j * this.tileHeight, this.tileHeight, this.tileHeight);
                table[i][j].setFill(Color.WHITE);
                table[i][j].setStroke(Color.LIGHTGRAY);
                table[i][j].setStyle("-fx-stroke-dash-array: 4;");
                group.getChildren().add(table[i][j]);
            }
        }
    }

    public void drawLines(ArrayList<Point> points) {
        Point lastPoint = new Point(0, 0);
        for (Point p : points) {
            group.getChildren().add(
                    new Line(
                            lastPoint.getX() * tileHeight,
                            (y - lastPoint.getY()) * tileHeight,
                            p.getX() * tileHeight,
                            (y - p.getY()) * tileHeight
                    )
            );
            lastPoint = p;
        }

    }

    public Group getGroup() {
        return group;
    }
}
