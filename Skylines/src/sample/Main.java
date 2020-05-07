package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sun.tools.jconsole.Tab;

import java.awt.*;
import java.util.*;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Table table = new Table(40, 25);
        root.setCenter(table.getGroup());

        run(table);

        primaryStage.setTitle("Skylines");
        primaryStage.setScene(new Scene(root, 1000, 900));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static void run(Table table) {
        System.out.println("input :");
        ArrayList<Wall> walls = input();
        MergeSort mergeSort = new MergeSort(walls);
        ArrayList<Wall> sortedWalls = mergeSort.getSortedWalls();
        ArrayList<Point> points = createPoints(sortedWalls);

        System.out.println(" sorted walls : ");
        for (Wall i : sortedWalls) {
            System.out.println(i);
        }
        System.out.println(" points : ");
        for (Point i : points) {
            System.out.println(i);
        }
        System.out.println("");

        table.drawLines(points);

    }

    public static ArrayList<Point> createPoints(ArrayList<Wall> sortedSkylines) {
        ArrayList<Point> points = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.add(0);

        for (Wall s : sortedSkylines) {
            if (s.getType() == Type.START) {
                if (s.getY() > pq.peek()) {
                    points.add(new Point(s.getX(), pq.peek()));
                    points.add(new Point(s.getX(), s.getY()));
                }
                pq.add(s.getY());
            } else { //TYPE.END
                if(s.getY() == pq.peek()){
                    pq.remove(s.getY());
                    points.add(new Point(s.getX(), s.getY()));
                    points.add(new Point(s.getX(), pq.peek()));
                }else {
                    pq.remove(s.getY());
                }
            }
        }
        return points;
    }

    public static ArrayList<Wall> input() {
        ArrayList<Wall> walls = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            String[] arr = str.split(",");
            walls.add(
                    new Wall(
                            Integer.parseInt(arr[0].substring(1)),
                            Integer.parseInt(arr[1]),
                            Type.START
                    )
            );
            walls.add(
                    new Wall(
                            Integer.parseInt(arr[2].substring(0, arr[2].length() - 1)),
                            Integer.parseInt(arr[1]),
                            Type.END
                    )
            );
        }
        System.out.println("done !");
        return walls;
    }
}
