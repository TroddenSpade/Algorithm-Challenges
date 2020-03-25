package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private BorderPane startPane;
    private BorderPane mosaicPane;

    private Scene startScene;
    private Scene mosaicScene;

    private int x, y, length, height;

    MosaicTable mosaicTable;

    @Override
    public void start(Stage primaryStage) throws Exception {
        startScreen(primaryStage);
        primaryStage.setTitle("Mosaicing");
        primaryStage.setScene(startScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void startScreen(Stage stage) {
        startPane = new BorderPane();
        startScene = new Scene(startPane, 500, 300);

        Label lengthLabel = new Label("Length :");
        VBox vBox = new VBox(10);
        HBox hBox1 = new HBox(10);
        HBox hBox2 = new HBox(10);
        TextField length = new TextField();
        Label xLabel = new Label("X :");
        Label yLabel = new Label("Y :");

        TextField x = new TextField();
        TextField y = new TextField();

        length.setMaxWidth(200);

        Button startButton = new Button("Next");
        startButton.setOnAction(e -> {
            try {
                this.length = Integer.parseInt(length.getText());
                this.x = Integer.parseInt(x.getText());
                this.y = Integer.parseInt(y.getText());
                this.mosaicTable = new MosaicTable(this.length, this.x, this.y);
                mosaicScreen(stage);
                stage.setScene(mosaicScene);
            } catch (Exception err) {
                System.out.println(err);
            }
        });

        hBox1.getChildren().addAll(lengthLabel, length);
        hBox2.getChildren().addAll(xLabel, x, yLabel, y);
        vBox.getChildren().addAll(hBox1, hBox2, startButton);

        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        startPane.setCenter(vBox);
    }

    public void mosaicScreen(Stage stage) {
        mosaicPane = new BorderPane();
        mosaicScene = new Scene(mosaicPane, 1000, 900);
        Label nOfMosaics = new Label("Number Of Mosaics :");
        HBox hBox = new HBox(20);

        Button calculate = new Button("calculate");
        calculate.setOnAction(e -> {
            mosaicTable.drawLines();
            nOfMosaics.setText("Number Of Mosaics : " +mosaicTable.getNumberOfMosaics());
        });

        hBox.getChildren().addAll(calculate,nOfMosaics);

        mosaicPane.setCenter(this.mosaicTable.getGroup());
        mosaicPane.setBottom(hBox);
    }
}
