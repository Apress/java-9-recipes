package org.java9recipes.chapter14.recipe14_14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Recipe 14-13: Organizing UI with Split Views
 * @author cdea
 * Update: J. Juneau
 */
public class OrganizingUIWithSplitViews extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(OrganizingUIWithSplitViews.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 14-14 Organizing UI with Split Views");
        Group root = new Group();
        Scene scene = new Scene(root, 350, 250, Color.WHITE);

        // Left and right split pane
        SplitPane splitPane = new SplitPane();
        splitPane.prefWidthProperty().bind(scene.widthProperty());
        splitPane.prefHeightProperty().bind(scene.heightProperty());

        //List<Node> items = splitPane.getItems();
        VBox leftArea = new VBox(10);

        for (int i = 0; i < 5; i++) {
            HBox rowBox = new HBox(20);
            final Text leftText = new Text();
            leftText.setText("Left " + i);
            leftText.setTranslateX(20);
            leftText.setFill(Color.BLUE);
            leftText.setFont(Font.font(null, FontWeight.BOLD, 20));

            rowBox.getChildren().add(leftText);
            leftArea.getChildren().add(rowBox);
        }
        leftArea.setAlignment(Pos.CENTER);

        // Upper and lower split pane
        SplitPane splitPane2 = new SplitPane();
        splitPane2.setOrientation(Orientation.VERTICAL);
        splitPane2.prefWidthProperty().bind(scene.widthProperty());
        splitPane2.prefHeightProperty().bind(scene.heightProperty());

        HBox centerArea = new HBox();

        InnerShadow iShadow = new InnerShadow();
        iShadow.setOffsetX(3.5f);
        iShadow.setOffsetY(3.5f);

        final Text upperRight = new Text();
        upperRight.setText("Upper Right");
        upperRight.setX(100);
        upperRight.setY(50);
        upperRight.setEffect(iShadow);
        upperRight.setFill(Color.LIME);
        upperRight.setFont(Font.font(null, FontWeight.BOLD, 35));
        upperRight.setTranslateY(50);
        centerArea.getChildren().add(upperRight);

        HBox rightArea = new HBox();

        final Text lowerRight = new Text();
        lowerRight.setText("Lower Right");
        lowerRight.setX(100);
        lowerRight.setY(50);
        lowerRight.setEffect(iShadow);
        lowerRight.setFill(Color.RED);
        lowerRight.setFont(Font.font(null, FontWeight.BOLD, 35));
        lowerRight.setTranslateY(50);
        rightArea.getChildren().add(lowerRight);

        splitPane2.getItems().add(centerArea);
        splitPane2.getItems().add(rightArea);

        // add left area
        splitPane.getItems().add(leftArea);

        // add right area
        splitPane.getItems().add(splitPane2);

        // evenly position divider
        ObservableList<SplitPane.Divider> dividers = splitPane.getDividers();
        for (int i = 0; i < dividers.size(); i++) {
            dividers.get(i).setPosition((i + 1.0) / 3);
        }

        HBox hbox = new HBox();
        hbox.getChildren().add(splitPane);
        root.getChildren().add(hbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

  
}
