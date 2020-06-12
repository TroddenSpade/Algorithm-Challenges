package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Group group = new Group();
        root.setCenter(group);
        input(group);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void input(Group group) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = input.nextInt();
            }
        }

        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i][j] != 0)
                    edges.add(new Edge(i, j, a[i][j]));
            }
        }

        Kruskal kruskal = new Kruskal(n, edges.size(), edges);
        ArrayList<Edge> kruskalEdges = kruskal.getF();

        draw(n, edges, kruskalEdges, group);

    }

    public static void draw(int n, ArrayList<Edge> edges, ArrayList<Edge> kruskal, Group group) {
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            Line l = new Line(
                    300 * Math.cos(Math.toRadians((int) (360 / n) * e.getI())),
                    300 * Math.sin(Math.toRadians((int) (360 / n) * e.getI())),
                    300 * Math.cos(Math.toRadians((int) (360 / n) * e.getJ())),
                    300 * Math.sin(Math.toRadians((int) (360 / n) * e.getJ()))
            );

            Text name = new Text("" + (e.getWeight()));
            name.setFill(Color.BLACK);
            StackPane sp = new StackPane();
            sp.getChildren().addAll(l, name);

            double y1 = 300 * Math.sin(Math.toRadians((int) (360 / n) * e.getI()));
            double y2 = 300 * Math.sin(Math.toRadians((int) (360 / n) * e.getJ()));
            double x1 = 300 * Math.cos(Math.toRadians((int) (360 / n) * e.getI()));
            double x2 = 300 * Math.cos(Math.toRadians((int) (360 / n) * e.getJ()));

            if ((y2 - y1) / (x2 - x1) >= 0) {
                sp.setLayoutX(x2 + 20);
                sp.setLayoutY(y2 + 20);
                group.getChildren().add(sp);
            } else {
                sp.setLayoutX((x2 - x1 >= 0 ? x1 : x2) + 20);
                sp.setLayoutY((y2 - y1 >= 0 ? y1 : y2) + 20);
                group.getChildren().add(sp);
            }
        }

        for (int i = 0; i < kruskal.size(); i++) {
            Edge e = kruskal.get(i);
            System.out.printf(e.toString());

            Line l = new Line(
                    300 * Math.cos(Math.toRadians((int) (360 / n) * e.getI())),
                    300 * Math.sin(Math.toRadians((int) (360 / n) * e.getI())),
                    300 * Math.cos(Math.toRadians((int) (360 / n) * e.getJ())),
                    300 * Math.sin(Math.toRadians((int) (360 / n) * e.getJ()))
            );
            l.setStroke(Color.RED);

            Text name = new Text("" + (e.getWeight()));
            name.setFill(Color.BLACK);
            StackPane sp = new StackPane();
            sp.getChildren().addAll(l, name);

            double y1 = 300 * Math.sin(Math.toRadians((int) (360 / n) * e.getI()));
            double y2 = 300 * Math.sin(Math.toRadians((int) (360 / n) * e.getJ()));
            double x1 = 300 * Math.cos(Math.toRadians((int) (360 / n) * e.getI()));
            double x2 = 300 * Math.cos(Math.toRadians((int) (360 / n) * e.getJ()));

            if ((y2 - y1) / (x2 - x1) >= 0) {
                sp.setLayoutX(x2 + 20);
                sp.setLayoutY(y2 + 20);
                group.getChildren().add(sp);
            } else {
                sp.setLayoutX((x2 - x1 >= 0 ? x1 : x2) + 20);
                sp.setLayoutY((y2 - y1 >= 0 ? y1 : y2) + 20);
                group.getChildren().add(sp);
            }
        }

        for (int i = 0; i < n; i++) {
            Circle c = new Circle(20);
            c.setFill(Color.GREY);
            Text name = new Text("" + (i + 1));
            name.setFill(Color.WHITE);
            StackPane sp = new StackPane();
            sp.getChildren().addAll(c, name);

            sp.setLayoutX(300 * Math.cos(Math.toRadians((int) (360 / n) * i)));
            sp.setLayoutY(300 * Math.sin(Math.toRadians((int) (360 / n) * i)));
            group.getChildren().add(sp);
        }
    }
}
