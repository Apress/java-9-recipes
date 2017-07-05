package org.java9recipes.chapter15.recipe15_05;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Recipe 15-5:  Enhancing with CSS
 * @author cdea
 * Update: J Juneau
 */
public class EnhancingWithCss extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
            primaryStage.setTitle("Chapter 15-5 Enhancing with CSS ");
            Group root = new Group();
            final Scene scene = new Scene(root, 640, 480, Color.BLACK);
            MenuBar menuBar = new MenuBar();
            Menu menu = new Menu("Look and Feel");
            
            // New Modena Look and Feel
            MenuItem modenaLnf = new MenuItem("Modena");
            modenaLnf.setOnAction(enableCss(STYLESHEET_MODENA,scene));
            menu.getItems().add(modenaLnf);
            
            // Old default, Caspian Look and Feel
            MenuItem caspianLnf = new MenuItem("Caspian");
            caspianLnf.setOnAction(enableCss(STYLESHEET_CASPIAN, scene));
            
            
            menu.getItems().add(caspianLnf);
            
            menu.getItems().add(createMenuItem("Control Style 1", "controlStyle1.css", scene));
            menu.getItems().add(createMenuItem("Control Style 2", "controlStyle2.css", scene));
            menu.getItems().add(createMenuItem("Sky", "sky.css", scene));       
            
            menuBar.getMenus().add(menu);
            // stretch menu
            menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
            
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
            VBox vbox = new VBox();
            vbox.getChildren().add(menuBar);
            vbox.getChildren().add(hbox);
            root.getChildren().add(vbox);
            splitPane.getItems().addAll(leftArea, rightGridPane); 

            primaryStage.setScene(scene);
            
            primaryStage.show();
        
    }
    
    protected final MenuItem createMenuItem(String label, String css, final Scene scene){
        MenuItem menuItem = new MenuItem(label);
        ObservableList<String> cssStyle = loadSkin(css);
        menuItem.setOnAction(skinForm(cssStyle, scene));
        return menuItem; 
    }
            
    protected final ObservableList<String> loadSkin(String cssFileName) {
        ObservableList<String> cssStyle = FXCollections.observableArrayList();
        cssStyle.addAll(getClass().getResource(cssFileName).toExternalForm());
        return cssStyle;
    }
    
    protected final EventHandler<ActionEvent> skinForm
        (final ObservableList<String> cssStyle, final Scene scene) {
        return (ActionEvent event) -> {
            scene.getStylesheets().clear();
            scene.getStylesheets().addAll(cssStyle);
        };
    }
    
    protected final EventHandler<ActionEvent> enableCss(String style, final Scene scene){
        return (ActionEvent event) -> {
           
            scene.getStylesheets().clear();
            setUserAgentStylesheet(style);
        };
    }

}
