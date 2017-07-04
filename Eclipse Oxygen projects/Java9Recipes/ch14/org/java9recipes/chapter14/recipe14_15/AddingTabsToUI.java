package org.java9recipes.chapter14.recipe14_15;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Recipe 14-15: Adding Tabs to a UI
 * @author cdea
 * Update: J. Juneau
 */
public class AddingTabsToUI extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 14-15 Adding Tabs to a UI");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 250, Color.WHITE);

        TabPane tabPane = new TabPane();

        MenuBar menuBar = new MenuBar();

        EventHandler<ActionEvent> action = changeTabPlacement(tabPane);

        Menu menu = new Menu("Tab Side");
        MenuItem left = new MenuItem("Left");

        left.setOnAction(action);
        menu.getItems().add(left);

        MenuItem right = new MenuItem("Right");
        right.setOnAction(action);
        menu.getItems().add(right);

        MenuItem top = new MenuItem("Top");
        top.setOnAction(action);
        menu.getItems().add(top);

        MenuItem bottom = new MenuItem("Bottom");
        bottom.setOnAction(action);
        menu.getItems().add(bottom);

        menuBar.getMenus().add(menu);

        BorderPane borderPane = new BorderPane();

        // generate 10 tabs
        for (int i = 0; i < 10; i++) {
            Tab tab = new Tab();
            tab.setText("Tab" + i);
            HBox hbox = new HBox();
            hbox.getChildren().add(new Label("Tab" + i));
            hbox.setAlignment(Pos.CENTER);
            tab.setContent(hbox);
            tabPane.getTabs().add(tab);
        }

        // add tab pane
        borderPane.setCenter(tabPane);

        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        // added menu bar
        borderPane.setTop(menuBar);

        // add border Pane
        root.getChildren().add(borderPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private EventHandler<ActionEvent> changeTabPlacement(final TabPane tabPane) {
        return (ActionEvent event) -> {
            MenuItem mItem = (MenuItem) event.getSource();
            String side = mItem.getText();
            if ("left".equalsIgnoreCase(side)) {
                tabPane.setSide(Side.LEFT);
            } else if ("right".equalsIgnoreCase(side)) {
                tabPane.setSide(Side.RIGHT);
            } else if ("top".equalsIgnoreCase(side)) {
                tabPane.setSide(Side.TOP);
            } else if ("bottom".equalsIgnoreCase(side)) {
                tabPane.setSide(Side.BOTTOM);
            }
        };
    }
}
