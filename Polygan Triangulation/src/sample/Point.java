package sample;

public class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Point p,double UNIT) {
        return Math.sqrt((x - p.getX())/UNIT * (x - p.getX())/UNIT + (y - p.getY())/UNIT * (y - p.getY())/UNIT);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
