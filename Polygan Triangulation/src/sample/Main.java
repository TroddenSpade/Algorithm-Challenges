package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    final double HEIGHT = 800;
    final double WIDTH = 1000;
    final static double UNIT = 20;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();

        int half = (int) (WIDTH / (UNIT * 2));

        for (int i = -half; i <= half; i++) {
            Text txt = new Text("" + i);
            txt.setLayoutX(i * UNIT);
            txt.setStroke(Color.LIGHTGREY);
            Line vls = new Line(i * UNIT, -HEIGHT / 2, i * UNIT, HEIGHT / 2);
            vls.setStroke(Color.LIGHTGREY);
            vls.setStyle("-fx-stroke-dash-array: 4;");
            group.getChildren().addAll(vls, txt);
        }

        half = (int) (HEIGHT / (UNIT * 2));

        for (int i = -half; i <= half; i++) {
            Text txt = new Text("" + -i);
            txt.setLayoutY(i * UNIT);
            txt.setStroke(Color.LIGHTGREY);
            Line hls = new Line(-WIDTH / 2, i * UNIT, WIDTH / 2, i * UNIT);
            hls.setStroke(Color.LIGHTGREY);
            hls.setStyle("-fx-stroke-dash-array: 4;");
            group.getChildren().addAll(hls, txt);
        }

        Line vl = new Line(0, -HEIGHT / 2, 0, HEIGHT / 2);
        Line hl = new Line(-WIDTH / 2, 0, WIDTH / 2, 0);
        hl.setStroke(Color.GRAY);
        vl.setStroke(Color.GRAY);
        group.getChildren().addAll(hl, vl);


        BorderPane pane = new BorderPane();

        double min = calculate(group);

        Label res = new Label("Minimum Distance of Polygon Triangulation : " + min);
        System.out.println(res.getText());
        pane.setBottom(res);
        pane.setCenter(group);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(pane, WIDTH, HEIGHT + 100));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static double calculate(Group group) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double x = input.nextInt() * UNIT;
            double y = input.nextInt() * UNIT;

            points.add(new Point(x, -y));
        }

        double[][] D = new double[n][n];
        ArrayList<Line>[][] diagonals = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                D[i][j] = 0;
                diagonals[i][j] = new ArrayList();

            }
        }

        for (int s = 3; s < n; s++) {
            for (int a = 0; a < n - s; a++) { // x = a , y = s + a
                D[a][s + a] = Integer.MAX_VALUE;
                for (int i = a + 1; i < s + a; i++) {
                    if (D[a][s + a] > D[a][i] + D[i][s + a] + dist(a, i, s + a, points)) {
                        D[a][s + a] = D[a][i] + D[i][s + a] + dist(a, i, s + a, points);
                        diagonals[a][s + a].clear();
                        diagonals[a][s + a].addAll(diagonals[a][i]);
                        diagonals[a][s + a].addAll(diagonals[i][a + s]);
                        addDiagonal(a, i, a + s, points, diagonals[a][s + a]);
                    }
                }
            }
        }

        for (int i = 0; i < points.size(); i++) {
            Line l = new Line(points.get(i).getX(),
                    points.get(i).getY(),
                    points.get((i + 1) % points.size()).getX(),
                    points.get((i + 1) % points.size()).getY());
            l.setStroke(Color.BLUE);
            l.setStyle("   -fx-stroke-width: 2; ");
            group.getChildren().add(l);

        }

        ArrayList<Line> ds = diagonals[0][n - 1];

        for (Line l : ds) {
            group.getChildren().add(l);
        }

        return D[0][n - 1];

    }

    public static void addDiagonal(int a, int k, int b, ArrayList<Point> points, ArrayList<Line> d) {
        if (k - a > 1) {
            Line l = new Line(points.get(a).getX(), points.get(a).getY(),
                    points.get(k).getX(), points.get(k).getY());
            l.setStroke(Color.GREEN);
            l.setStyle("   -fx-stroke-width: 1.5; ");

            d.add(l);
        }

        if (b - k > 1) {
            Line l = new Line(points.get(k).getX(), points.get(k).getY(),
                    points.get(b).getX(), points.get(b).getY());
            l.setStroke(Color.GREEN);
            l.setStyle("   -fx-stroke-width: 1.5; ");

            d.add(l);
        }
    }

    public static double dist(int a, int k, int b, ArrayList<Point> points) {
        double sum = 0;
        if (k - a > 1) {
            sum += points.get(k).distance(points.get(a),UNIT);
        }

        if (b - k > 1) {
            sum += points.get(k).distance(points.get(b),UNIT);
        }

        return sum;
    }
}
