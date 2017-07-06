package org.java9recipes.chapter14.recipe14_05;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Assigning Colors To Objects
 * @author cdea
 * Update:  J. Juneau
 */
public class AssigningColorsToObjects extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 14-5 Assigning Colors To Objects");
        Group root = new Group();
        Scene scene = new Scene(root, 350, 300, Color.WHITE);


        Ellipse ellipse = new Ellipse(100, 50 + 70/2, 50, 70/2);
        RadialGradient gradient1 = new RadialGradient(0,
                                                      .1,    // focus angle
                                                      80,    // focus distance
                                                      45,    // centerX
                                                      120,   // centerY
                                                      false, // proportional
                                                      CycleMethod.NO_CYCLE,
                                                      new Stop(0, Color.RED), new Stop(1, Color.BLACK));

        ellipse.setFill(gradient1);
        root.getChildren().add(ellipse); 

        // Create line
        Line blackLine = new Line();
        blackLine.setStartX(170);
        blackLine.setStartY(30);
        blackLine.setEndX(20);
        blackLine.setEndY(140);
        blackLine.setFill(Color.BLACK);
        blackLine.setStrokeWidth(10.0f);
        blackLine.setTranslateY(ellipse.prefHeight(-1) + ellipse.getLayoutY() + 10);

        root.getChildren().add(blackLine); 

        // Create rectangle
        Rectangle rectangle = new Rectangle();
        rectangle.setX(50);
        rectangle.setY(50);
        rectangle.setWidth(100);
        rectangle.setHeight(70);
        rectangle.setTranslateY(ellipse.prefHeight(-1) + ellipse.getLayoutY() + 10);

        // Create linear gradient
        LinearGradient linearGrad = new LinearGradient(50,     //startX
                                                       50,     //startY
                                                       50,     //endX
                                                       50 + rectangle.prefHeight(-1) + 25,     //endY
                                                       false,  //proportional
                                                       CycleMethod.NO_CYCLE,
                                                       new Stop(0.1f, Color.rgb(255, 200, 0, .784)),
                                                        new Stop(1.0f, Color.rgb(0, 0, 0, .784)));

        rectangle.setFill(linearGrad);
        root.getChildren().add(rectangle); 

        // Create rectangle with rounded corners
        Rectangle roundRect = new Rectangle();
        roundRect.setX(50);
        roundRect.setY(50);
        roundRect.setWidth(100);
        roundRect.setHeight(70);
        roundRect.setArcWidth(20);
        roundRect.setArcHeight(20);
        roundRect.setTranslateY(ellipse.prefHeight(-1) + 
                            ellipse.getLayoutY() + 
                            10 + 
                            roundRect.prefHeight(-1) + 
                            roundRect.getLayoutY() + 10);

        LinearGradient cycleGrad = new LinearGradient(50,
                                                      50,
                                                      70,
                                                      70,
                                                      false,
                                                      CycleMethod.REFLECT,
                                                      new Stop(0f, Color.rgb(0, 255, 0, .784)),
                                                      new Stop(1.0f, Color.rgb(0, 0, 0, .784)));


        roundRect.setFill(cycleGrad);
        root.getChildren().add(roundRect);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
