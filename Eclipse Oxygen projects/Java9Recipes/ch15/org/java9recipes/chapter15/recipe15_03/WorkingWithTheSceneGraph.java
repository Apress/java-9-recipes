package org.java9recipes.chapter15.recipe15_03;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Recipe 15-3: Working with the Scene Graph
 * @author cdea
 * Update: J Juneau
 */
public class WorkingWithTheSceneGraph extends Application {
    
    Path onePath = new Path();
    Point2D anchorPt;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 15-3 Working with the Scene Graph");
        
        final Group root = new Group();        
        // add path
        root.getChildren().add(onePath);
        
        final Scene scene = new Scene(root, 300, 250);
        scene.setFill(Color.WHITE);
        
        RadialGradient gradient1 = new RadialGradient(0,
                .1,
                100,
                100,
                20,
                false,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED),
                new Stop(1, Color.BLACK));
        
        // create a sphere
        final Circle sphere = new Circle();
        sphere.setCenterX(100);
        sphere.setCenterY(100);
        sphere.setRadius(20);
        sphere.setFill(gradient1);

        // add sphere
        root.getChildren().add(sphere);
       
        // animate sphere by following the path.
        final PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setCycleCount(1);
        pathTransition.setNode(sphere);
        pathTransition.setPath(onePath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        // once finished clear path
        pathTransition.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent event) -> {
            onePath.getElements().clear();
        });

        // starting initial path
        scene.onMousePressedProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            onePath.getElements().clear();
            // start point in path
            anchorPt = new Point2D(event.getX(), event.getY());
            onePath.setStrokeWidth(3);
            onePath.setStroke(Color.BLACK);
            onePath.getElements().add(new MoveTo(anchorPt.getX(), anchorPt.getY()));
        });
        
        // dragging creates lineTos added to the path
        scene.onMouseDraggedProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            onePath.getElements().add(new LineTo(event.getX(), event.getY()));
        });
        
        // end the path when mouse released event
        scene.onMouseReleasedProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            onePath.setStrokeWidth(0);
            if (onePath.getElements().size() > 1) {
                pathTransition.stop();
                pathTransition.playFromStart();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

