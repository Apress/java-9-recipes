package org.java9recipes.chapter15.recipe15_01;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Recipe 15-1: Creating Images
 *
 * @author cdea Update: J Juneau
 */
public class CreatingImages extends Application {

    private final List<String> imageFiles = new ArrayList<>();
    private int currentIndex = -1;
    private final String filePrefix = "file:";

    public enum ButtonMove {

        NEXT, PREV
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 15-1 Creating a Image");
        Group root = new Group();
        Scene scene = new Scene(root, 551, 400, Color.BLACK);

        // image view
        final ImageView currentImageView = new ImageView();

        // maintain aspect ratio
        currentImageView.setPreserveRatio(true);

        // resize based on the scene
        currentImageView.fitWidthProperty().bind(scene.widthProperty());

        final HBox pictureRegion = new HBox();
        pictureRegion.getChildren().add(currentImageView);
        root.getChildren().add(pictureRegion);

        // Dragging over surface
        scene.setOnDragOver((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        });

        // Dropping over surface
        scene.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;
                String filePath = null;
                for (File file : db.getFiles()) {
                    filePath = file.getAbsolutePath();
                    System.out.println(filePath);
                    currentIndex += 1;
                    imageFiles.add(currentIndex, filePath);
                }
                filePath = filePrefix + filePath;
                // set new image as the image to show.
                Image imageimage = new Image(filePath);
                currentImageView.setImage(imageimage);

            }
            event.setDropCompleted(success);
            event.consume();

        });

        // create slide controls
        Group buttonGroup = new Group();

        // rounded rect
        Rectangle buttonArea = new Rectangle();
        buttonArea.setArcWidth(15);
        buttonArea.setArcHeight(20);
        buttonArea.setFill(new Color(0, 0, 0, .55));
        buttonArea.setX(0);
        buttonArea.setY(0);
        buttonArea.setWidth(60);
        buttonArea.setHeight(30);
        buttonArea.setStroke(Color.rgb(255, 255, 255, .70));

        buttonGroup.getChildren().add(buttonArea);
        // left control
        Arc leftButton = new Arc();
        leftButton.setType(ArcType.ROUND);
        leftButton.setCenterX(12);
        leftButton.setCenterY(16);
        leftButton.setRadiusX(15);
        leftButton.setRadiusY(15);
        leftButton.setStartAngle(-30);
        leftButton.setLength(60);
        leftButton.setFill(new Color(1, 1, 1, .90));

        leftButton.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent me) -> {
            int indx = gotoImageIndex(ButtonMove.PREV);
            if (indx > -1) {
                String namePict = imageFiles.get(indx);
                namePict = filePrefix + namePict;
                final Image image = new Image(namePict);
                currentImageView.setImage(image);
            }
        });
        buttonGroup.getChildren().add(leftButton);

        // right control
        Arc rightButton = new Arc();
        rightButton.setType(ArcType.ROUND);
        rightButton.setCenterX(12);
        rightButton.setCenterY(16);
        rightButton.setRadiusX(15);
        rightButton.setRadiusY(15);
        rightButton.setStartAngle(180 - 30);
        rightButton.setLength(60);
        rightButton.setFill(new Color(1, 1, 1, .90));
        rightButton.setTranslateX(40);
        buttonGroup.getChildren().add(rightButton);

        rightButton.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent me) -> {
            int indx = gotoImageIndex(ButtonMove.NEXT);
            if (indx > -1) {
                String namePict = imageFiles.get(indx);
                namePict = filePrefix + namePict;
                final Image image = new Image(namePict);
                currentImageView.setImage(image);
            }
        });

        // move button group when scene is resized
        buttonGroup.translateXProperty().bind(scene.widthProperty().subtract(buttonArea.getWidth() + 6));
        buttonGroup.translateYProperty().bind(scene.heightProperty().subtract(buttonArea.getHeight() + 6));
        root.getChildren().add(buttonGroup);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Returns the next index in the list of files to go to next.
     *
     * @param direction PREV and NEXT to move backward or forward in the list of
     * pictures.
     * @return int the index to the previous or next picture to be shown.
     */
    public int gotoImageIndex(ButtonMove direction) {
        int size = imageFiles.size();
        if (size == 0) {
            currentIndex = -1;
        } else if (direction == ButtonMove.NEXT && size > 1 && currentIndex < size - 1) {
            currentIndex += 1;
        } else if (direction == ButtonMove.PREV && size > 1 && currentIndex > 0) {
            currentIndex -= 1;
        }

        return currentIndex;
    }

}
