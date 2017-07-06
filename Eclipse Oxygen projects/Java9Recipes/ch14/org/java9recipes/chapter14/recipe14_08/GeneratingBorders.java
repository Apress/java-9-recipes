package org.java9recipes.chapter14.recipe14_08;


import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Recipe 14-08: Generating Borders
 * <p>
 * +-------------------------+ | label label | | +--------+ +--------+ | | [ ] [
 * ] | | +--------+ +--------+ | | [button] | +-------------------------+
 *
 * </p>
 *
 * @author cdea Update: J. Juneau
 */
public class GeneratingBorders extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 14-8 Generating Borders");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 330, Color.WHITE);

        // create a grid pane
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        // label CSS Editor
        Label cssEditorLbl = new Label("CSS Editor");
        GridPane.setHalignment(cssEditorLbl, HPos.CENTER);
        gridpane.add(cssEditorLbl, 0, 0);

        // label Border View
        Label borderLbl = new Label("Border View");
        GridPane.setHalignment(borderLbl, HPos.CENTER);
        gridpane.add(borderLbl, 1, 0);

        // Text area for CSS editor
        final TextArea cssEditorFld = new TextArea();
        cssEditorFld.setPrefRowCount(10);
        cssEditorFld.setPrefColumnCount(100);
        cssEditorFld.setWrapText(true);
        cssEditorFld.setPrefWidth(150);
        GridPane.setHalignment(cssEditorFld, HPos.CENTER);
        gridpane.add(cssEditorFld, 0, 1);

        String cssDefault = "-fx-border-color: blue;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: dashed;\n";

        cssEditorFld.setText(cssDefault);

        // Border decorate the picture
        final ImageView imv = new ImageView();
        final Image image2 = new Image(GeneratingBorders.class.getResourceAsStream("smoke_glass_buttons1.png"));
       // Use the following implementation within an IDE to hard code the path of the image, if needed.
//        Image image2 = null;
//        try {
//            image2 = new Image(new FileInputStream("/path-to-project/org/java9recipes/chapter14/recipe14_08/smoke_glass_buttons1.png"));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(GeneratingBorders.class.getName()).log(Level.SEVERE, null, ex);
//        }
        imv.setImage(image2);

        final HBox pictureRegion = new HBox();
        pictureRegion.setStyle(cssDefault);
        pictureRegion.getChildren().add(imv);
        gridpane.add(pictureRegion, 1, 1);

        Button apply = new Button("Bling!");
        GridPane.setHalignment(apply, HPos.RIGHT);
        gridpane.add(apply, 0, 2);

        apply.setOnAction((e) -> {
            pictureRegion.setStyle(cssEditorFld.getText());

        });

        root.getChildren().add(gridpane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
