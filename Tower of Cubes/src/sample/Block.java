package sample;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class Block {
    public int bottom;
    public int top;
    public int w;

    Block(int w,int top,int bottom){
        this.w = w;
        this.top = top;
        this.bottom = bottom;
    }

    public int getW() {
        return w;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    @Override
    public String toString() {
        return  "{ Weight : " + w +
                "->  Bottom : " + bottom +
                ", Top : " + top + " }";
    }

    public StackPane getBox(double size){

        Box box = new Box(size, size, size);
        Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
        Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
        rxBox.setAngle(30);
        ryBox.setAngle(30);
        rzBox.setAngle(0);
        box.getTransforms().addAll(rxBox, ryBox, rzBox);

        Text text = new Text(this.toString());
        StackPane stack = new StackPane();
        stack.getChildren().addAll(box, text);

        return stack;
    }

}
