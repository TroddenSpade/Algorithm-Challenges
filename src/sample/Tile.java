package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Tile extends Rectangle {

    private int x;
    private int y;

    public Tile(int x, int y, int height) {
        super(x * height, y * height, height, height);
        this.x = x;
        this.y = y;
        this.setFill(Color.WHITE);
        this.setStroke(Color.LIGHTGRAY);
        this.setStyle("-fx-stroke-dash-array: 4;");
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void toCyan(){
        this.setFill(Color.CYAN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return x == tile.x &&
                y == tile.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}