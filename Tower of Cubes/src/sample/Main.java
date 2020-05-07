package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    private static final double HEIGHT = 800;

    private BorderPane pane = new BorderPane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Group group = new Group();
        run(group);

        root.setCenter(group);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void run(Group group) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int weight = input.nextInt();
            int fr = input.nextInt();
            int ba = input.nextInt();
            int le = input.nextInt();
            int ri = input.nextInt();
            int to = input.nextInt();
            int bo = input.nextInt();
            blocks.add(new Block(weight, fr, ba));
            blocks.add(new Block(weight, le, ri));
            blocks.add(new Block(weight, to, bo));
            blocks.add(new Block(weight, ba, fr));
            blocks.add(new Block(weight, ri, le));
            blocks.add(new Block(weight, bo, to));
        }

        MergeSort mergeSort = new MergeSort(blocks);
        ArrayList<Block> sortedBlocks = mergeSort.getBlocks();

        Graph graph = new Graph(sortedBlocks.size());

        graph.create(sortedBlocks);

        ArrayList<Integer> list = graph.longestPath();
        final double SIZE = Math.min((HEIGHT-100) / list.size(), 100);
        for (int i = 0; i < list.size(); i++) {
            StackPane sp = sortedBlocks.get(list.get(i)).getBox(SIZE);
            sp.setLayoutY(-i * SIZE);
            group.getChildren().add(sp);
            System.out.println(sortedBlocks.get(list.get(i)));
        }

    }

}
