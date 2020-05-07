package sample;

enum Type {
    START, END
}

public class Wall {

    private int x, y;
    private Type type;

    public Wall(int x, int y, Type type) {
        this.x = x;
        this.type = type;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                '}';
    }
}
