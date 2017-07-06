package org.java9recipes.chapter14.recipe14_12;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Recipe 14-12: Associating Keyboard Sequences
 *
 * @author cdea Update: J. Juneau
 */
public class AssociatingKeyboardSequences extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 14-12 Associating Keyboard Sequences");
        Group root = new Group();
        Scene scene = new Scene(root, 530, 300, Color.WHITE);

        final StringProperty statusProperty = new SimpleStringProperty();

        InnerShadow iShadow = new InnerShadow();
        iShadow.setOffsetX(3.5f);
        iShadow.setOffsetY(3.5f);

        final Text status = new Text();
        status.setEffect(iShadow);
        status.setX(100);
        status.setY(50);
        status.setFill(Color.LIME);
        status.setFont(Font.font(null, FontWeight.BOLD, 35));
        status.setTranslateY(50);

        status.textProperty().bind(statusProperty);
        statusProperty.set("Keyboard Shortcuts \nCtrl-N, \nCtrl-S, \nCtrl-X");
        root.getChildren().add(status);

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.getChildren().add(menuBar);

        Menu menu = new Menu("File");
        menuBar.getMenus().add(menu);

        MenuItem newItem = new MenuItem();
        newItem.setText("New");
        newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        newItem.setOnAction((e) -> {
            statusProperty.set("Ctrl-N");
        });
        menu.getItems().add(newItem);

        MenuItem saveItem = new MenuItem();
        saveItem.setText("Save");
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveItem.setOnAction((e) -> {
            statusProperty.set("Ctrl-S");
        });
        menu.getItems().add(saveItem);

        menu.getItems().add(new SeparatorMenuItem());

        MenuItem exitItem = new MenuItem();
        exitItem.setText("Exit");
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        exitItem.setOnAction((e) -> {
            statusProperty.set("Ctrl-X");
        });
        menu.getItems().add(exitItem);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
