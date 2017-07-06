package org.java9recipes.chapter14.recipe14_04;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/**
 * Creating Shapes
 * @author cdea
 * Update: J. Juneau
 */
public class CreatingShapes extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 14-4 Creating Shapes");
        Group root = new Group();
        Scene scene = new Scene(root, 306, 550, Color.WHITE);

        // CubicCurve
        CubicCurve cubicCurve = new CubicCurve();
        cubicCurve.setStartX(50);
        cubicCurve.setStartY(75); // start pt (x1,y1)
        cubicCurve.setControlX1(80);
        cubicCurve.setControlY1(-25);// control pt1
        cubicCurve.setControlX2(110);
        cubicCurve.setControlY2(175);  // control pt2
        cubicCurve.setEndX(140);
        cubicCurve.setEndY(75);
        cubicCurve.setStrokeType(StrokeType.CENTERED);
        cubicCurve.setStrokeWidth(1);
        cubicCurve.setStroke(Color.BLACK);
        cubicCurve.setStrokeWidth(3);
        cubicCurve.setFill(Color.WHITE);

        root.getChildren().add(cubicCurve);

        // Ice cream 
        Path path = new Path();

        MoveTo moveTo = new MoveTo();
        moveTo.setX(50);
        moveTo.setY(150);

        QuadCurveTo quadCurveTo = new QuadCurveTo();
        quadCurveTo.setX(150);
        quadCurveTo.setY(150);
        quadCurveTo.setControlX(100);
        quadCurveTo.setControlY(50);

        LineTo lineTo1 = new LineTo();
        lineTo1.setX(50);
        lineTo1.setY(150);

        LineTo lineTo2 = new LineTo();
        lineTo2.setX(100);
        lineTo2.setY(275);

        LineTo lineTo3 = new LineTo();
        lineTo3.setX(150);
        lineTo3.setY(150);
        path.getElements().add(moveTo);
        path.getElements().add(quadCurveTo);
        path.getElements().add(lineTo1);
        path.getElements().add(lineTo2);
        path.getElements().add(lineTo3);
        path.setTranslateY(30);
        path.setStrokeWidth(3);
        path.setStroke(Color.BLACK);

        root.getChildren().add(path);

        // QuadCurve create a smile
        QuadCurve quad = new QuadCurve();
        quad.setStartX(50);
        quad.setStartY(50);
        quad.setEndX(150);
        quad.setEndY(50);
        quad.setControlX(125);
        quad.setControlY(150);
        quad.setTranslateY(path.getBoundsInParent().getMaxY());
        quad.setStrokeWidth(3);
        quad.setStroke(Color.BLACK);
        quad.setFill(Color.WHITE);

        root.getChildren().add(quad);

        // outer donut
        Ellipse bigCircle = new Ellipse(100, 100, 50, 75/2);
        //bigCircle.setTranslateY(quad.getBoundsInParent().getMaxY());
        bigCircle.setStrokeWidth(3);
        bigCircle.setStroke(Color.BLACK);
        bigCircle.setFill(Color.WHITE);

        // donut hole
        Ellipse smallCircle = new Ellipse(100, 100, 35/2, 25/2);

        // make a donut
        Shape donut = Path.subtract(bigCircle, smallCircle);
        donut.setStrokeWidth(1);
        donut.setStroke(Color.BLACK);
        // orange glaze
        donut.setFill(Color.rgb(255, 200, 0));

        // add drop shadow
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0f);
        dropShadow.setOffsetY(2.0f);
        dropShadow.setColor(Color.rgb(50, 50, 50, .588));

        donut.setEffect(dropShadow);

        // move slightly down for spacing
        donut.setTranslateY(quad.getBoundsInParent().getMinY() + 10);

        root.getChildren().add(donut);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
