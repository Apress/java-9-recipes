
package org.java9recipes.chapter14.recipe14_03;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Changing Text Fonts
 * @author cdea
 * Updated: J. Juneau
 */
public class ChangingTextFonts extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 14-3 Changing Text Fonts");
        Group root = new Group();
        Scene scene = new Scene(root, 330, 250, Color.WHITE);

        // Serif with drop shadow
        Text java8Recipes2 = new Text(50, 50, "Java 8 Recipes");
        Font serif = Font.font("Serif", 30);
        java8Recipes2.setFont(serif);
        java8Recipes2.setFill(Color.RED);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0f);
        dropShadow.setOffsetY(2.0f);
        dropShadow.setColor(Color.rgb(50, 50, 50, .588));
        java8Recipes2.setEffect(dropShadow);
        root.getChildren().add(java8Recipes2);


        // SanSerif
        Text java8Recipes3 = new Text(50, 100, "Java 8 Recipes");
        Font sanSerif = Font.font("SanSerif", 30);
        java8Recipes3.setFont(sanSerif);
        java8Recipes3.setFill(Color.BLUE);
        root.getChildren().add(java8Recipes3);

        // Dialog
        Text java8Recipes4 = new Text(50, 150, "Java 8 Recipes");
        Font dialogFont = Font.font("Dialog", 30);
        java8Recipes4.setFont(dialogFont);
        java8Recipes4.setFill(Color.rgb(0, 255, 0));
        root.getChildren().add(java8Recipes4);

        // Monospaced
        Text java8Recipes5 = new Text(50, 200, "Java 8 Recipes");
        Font monoFont = Font.font("Monospaced", 30);
        java8Recipes5.setFont(monoFont);
        java8Recipes5.setFill(Color.BLACK);
        root.getChildren().add(java8Recipes5);

        Reflection refl = new Reflection();
        refl.setFraction(0.8f);
        java8Recipes5.setEffect(refl);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
