/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java9recipes.chapter14.recipe14_03;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Recipe 14-3:  Changing Text Fonts w FXML and CSS
 * @author Juneau
 */
public class ChangingTextFontsSolution2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chapter 14-3 Changing Text Fonts Using TextFlow and FXML");
        stage.setScene((Scene) FXMLLoader.load(getClass().getResource("textfonts.fxml")));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
