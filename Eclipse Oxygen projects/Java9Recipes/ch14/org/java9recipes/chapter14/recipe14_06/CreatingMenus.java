package org.java9recipes.chapter14.recipe14_06;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Recipe 14-6: Creating Menus
 * @author cdea
 * Updated: J. Juneau
 */
public class CreatingMenus extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 14-6 Creating Menus");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.WHITE);

        MenuBar menuBar = new MenuBar();

        // File menu - new, save, exit
        Menu menu = new Menu("File");
        menu.getItems().add(new MenuItem("New"));
        menu.getItems().add(new MenuItem("Save"));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(new MenuItem("Exit"));

        menuBar.getMenus().add(menu);

        // Cameras menu - camera 1, camera 2
        Menu tools = new Menu("Cameras");
        CheckMenuItem item1 = new CheckMenuItem();
        item1.setText("Show Camera 1");
        item1.setSelected(true);
        tools.getItems().add(item1);

        CheckMenuItem item2 = new CheckMenuItem();
        item2.setText("Show Camera 2");
        item2.setSelected(true);
        tools.getItems().add(item2);

        menuBar.getMenus().add(tools);


        // Alarm
        Menu alarm = new Menu("Alarm");
        ToggleGroup tGroup = new ToggleGroup();

        RadioMenuItem soundAlarmItem = new RadioMenuItem();
        soundAlarmItem.setToggleGroup(tGroup);
        soundAlarmItem.setText("Sound Alarm");

        RadioMenuItem stopAlarmItem = new RadioMenuItem();
        stopAlarmItem.setToggleGroup(tGroup);
        stopAlarmItem.setText("Alarm Off");
        stopAlarmItem.setSelected(true);

        alarm.getItems().add(soundAlarmItem);
        alarm.getItems().add(stopAlarmItem);

        Menu contingencyPlans = new Menu("Contingent Plans");
        contingencyPlans.getItems().add(new CheckMenuItem("Self Destruct in T minus 50"));
        contingencyPlans.getItems().add(new CheckMenuItem("Turn off the coffee machine "));
        contingencyPlans.getItems().add(new CheckMenuItem("Run for your lives! "));

        alarm.getItems().add(contingencyPlans);
        menuBar.getMenus().add(alarm);

        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        root.getChildren().add(menuBar); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
