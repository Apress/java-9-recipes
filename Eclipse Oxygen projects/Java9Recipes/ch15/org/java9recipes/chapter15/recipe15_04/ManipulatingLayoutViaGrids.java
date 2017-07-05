package org.java9recipes.chapter15.recipe15_04;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Recipe 15-4: Manipulating Layout Via Grids
 * @author cdea
 * Update: J. Juneau
 */
public class ManipulatingLayoutViaGrids extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 15-4 Manipulating Layout via Grids ");
        Group root = new Group();
        Scene scene = new Scene(root, 640, 480, Color.WHITE);

        // Left and right split pane
        SplitPane splitPane = new SplitPane();
        splitPane.prefWidthProperty().bind(scene.widthProperty());
        splitPane.prefHeightProperty().bind(scene.heightProperty());
        
        // Form on the right
        GridPane rightGridPane = new MyForm();
        
        GridPane leftGridPane = new GridPaneControlPanel(rightGridPane);
        
        VBox leftArea = new VBox(10);
        leftArea.getChildren().add(leftGridPane);
        HBox hbox = new HBox();
        hbox.getChildren().add(splitPane);
        root.getChildren().add(hbox);
        splitPane.getItems().addAll(leftArea, rightGridPane); 
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    
}

