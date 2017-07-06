package org.java9recipes.chapter15.recipe15_02;

import java.util.ArrayList;

import java.util.List;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Recipe 15-2: Generating an Animation
 * @author cdea
 * Update: J Juneau
 */
public class GeneratingAnAnimation extends Application {

    List<String> imagesFiles = new ArrayList<>();
    int currentIndexImageFile = -1;
    public static int NEXT = 1;
    public static int PREV = -1;
    private final String filePrefix = "file:";
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 15-2 Generating an Animation");
        Group root = new Group();
        final Scene scene = new Scene(root, 300, 250, Color.BLACK);

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
                db.getFiles().stream().map((file) -> file.getAbsolutePath()).map((filePath) -> {
                    Image imageimage = new Image(filePrefix + filePath);
                    currentImageView.setImage(imageimage);
                    currentIndexImageFile += 1;
                    return filePath;
                }).map((filePath) -> {
                    imagesFiles.add(currentIndexImageFile, filePrefix + filePath);
                    return filePath;
                }).map((filePath) -> {
                    System.out.println("Dropfile: " + filePrefix + filePath);
                    return filePath;
                }).forEach((_item) -> {
                    System.out.println("currentIndexImageFile: " + currentIndexImageFile);
                });
            }
            event.setDropCompleted(success);
            event.consume();
        });

        // create slide controls
        final Group buttonGroup = new Group();

        // rounded rect
        Rectangle buttonRect = new Rectangle();
        buttonRect.setArcWidth(15);
        buttonRect.setArcHeight(20);
        buttonRect.setFill(new Color(0, 0, 0, .55));
        buttonRect.setX(0);
        buttonRect.setY(0);
        buttonRect.setWidth(60);
        buttonRect.setHeight(30);
        buttonRect.setStroke(Color.rgb(255, 255, 255, .70));

        buttonGroup.getChildren().add(buttonRect);
        // previous button
        Arc prevButton = new Arc();
        prevButton.setType(ArcType.ROUND);
        prevButton.setCenterX(12);
        prevButton.setCenterY(16);
        prevButton.setRadiusX(15);
        prevButton.setRadiusY(15);
        prevButton.setStartAngle(-30);
        prevButton.setLength(60);
        prevButton.setFill(new Color(1, 1, 1, .90));
        buttonGroup.getChildren().add(prevButton);
        
        prevButton.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent me) -> {
            int indx = gotoImageIndex(PREV);
            if (indx > -1) {
                String namePict = imagesFiles.get(indx);
                final Image nextImage = new Image(namePict);
                SequentialTransition seqTransition = transitionByFading(nextImage, currentImageView);
                seqTransition.play();
            }
        });

        // next button
        Arc nextButton = new Arc();
        nextButton.setType(ArcType.ROUND);
        nextButton.setCenterX(12);
        nextButton.setCenterY(16);
        nextButton.setRadiusX(15);
        nextButton.setRadiusY(15);
        nextButton.setStartAngle(180 - 30);
        nextButton.setLength(60);
        nextButton.setFill(new Color(1, 1, 1, .90));
        nextButton.setTranslateX(40);
        buttonGroup.getChildren().add(nextButton);

        nextButton.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent me) -> {
            int indx = gotoImageIndex(NEXT);
            if (indx > -1) {
                String namePict = imagesFiles.get(indx);
                final Image nextImage = new Image(namePict);
                SequentialTransition seqTransition = transitionByFading(nextImage, currentImageView);
                seqTransition.play();

            }
        });

        // move button group when scene is resized
        buttonGroup.translateXProperty().bind(scene.widthProperty().subtract(buttonRect.getWidth() + 6));
        buttonGroup.translateYProperty().bind(scene.heightProperty().subtract(buttonRect.getHeight() + 6));
        root.getChildren().add(buttonGroup);

        // Fade in button controls
        scene.setOnMouseEntered((MouseEvent me) -> {
            FadeTransition fadeButtons = new FadeTransition(Duration.millis(500), buttonGroup);
            fadeButtons.setFromValue(0.0);
            fadeButtons.setToValue(1.0);
            fadeButtons.play();
        });

        // Fade out button controls
        scene.setOnMouseExited((MouseEvent me) -> {
            FadeTransition fadeButtons = new FadeTransition(Duration.millis(500), buttonGroup);
            fadeButtons.setFromValue(1);
            fadeButtons.setToValue(0);
            fadeButtons.play();
        });

        // create ticker area
        final Group tickerArea = new Group();
        final Rectangle tickerRect = new Rectangle();
        tickerRect.setArcWidth(15);
        tickerRect.setArcHeight(20);
        tickerRect.setFill(new Color(0, 0, 0, .55));
        tickerRect.setX(0);
        tickerRect.setY(0);
        tickerRect.setWidth(scene.getWidth() - 6);
        tickerRect.setHeight(30);
        tickerRect.setStroke(Color.rgb(255, 255, 255, .70));


        Rectangle clipRegion = new Rectangle();
        clipRegion.setArcWidth(15);
        clipRegion.setArcHeight(20);
        clipRegion.setX(0);
        clipRegion.setY(0);
        clipRegion.setWidth(scene.getWidth() - 6);
        clipRegion.setHeight(30);
        clipRegion.setStroke(Color.rgb(255, 255, 255, .70));

        tickerArea.setClip(clipRegion);

        // Resize the ticker area when the window is resized
        tickerArea.setTranslateX(6);
        tickerArea.translateYProperty().bind(scene.heightProperty().subtract(tickerRect.getHeight() + 6));
        tickerRect.widthProperty().bind(scene.widthProperty().subtract(buttonRect.getWidth() + 16));
        clipRegion.widthProperty().bind(scene.widthProperty().subtract(buttonRect.getWidth() + 16));
        tickerArea.getChildren().add(tickerRect);


        root.getChildren().add(tickerArea);


        // add news text
        Text news = new Text();
        news.setText("JavaFX 8 News Ticker... | New Features: Swing Node, Event Dispatch Thread and JavaFX Application Thread Merge,  " +
                "New Look and Feel - Modena, Rich Text Support, Printing, Tree Table Control, Much More!");
        news.setTranslateY(18);
        news.setFill(Color.WHITE);
        tickerArea.getChildren().add(news);



        final TranslateTransition ticker = new TranslateTransition();
        ticker.setNode(news);
        int newsLength = news.getText().length();
        ticker.setDuration(Duration.millis((newsLength * 4/300) * 15000));
        ticker.setFromX(scene.widthProperty().doubleValue());
        ticker.setToX(-scene.widthProperty().doubleValue() - (newsLength * 5));
        ticker.setFromY(19);
        ticker.setInterpolator(Interpolator.LINEAR);
        ticker.setCycleCount(1);

        // when ticker has finished reset and replay ticker animation
        ticker.setOnFinished((ActionEvent ae) -> {
            ticker.stop();
            ticker.setFromX(scene.getWidth());
            ticker.setDuration(new Duration((newsLength * 4/300) * 15000));
            ticker.playFromStart();
        });

        tickerArea.setOnMouseEntered((MouseEvent me) -> {
            ticker.pause();
        });

        tickerArea.setOnMouseExited((MouseEvent me) -> {
            ticker.play();
        });

        ticker.play();
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public int gotoImageIndex(int direction) {
        int size = imagesFiles.size();
        if (size == 0) {
            currentIndexImageFile = -1;
        } else if (direction == NEXT && size > 1 && currentIndexImageFile < size - 1) {
            currentIndexImageFile += 1;
        } else if (direction == PREV && size > 1 && currentIndexImageFile > 0) {
            currentIndexImageFile -= 1;
        }

        return currentIndexImageFile;
    }
    public SequentialTransition transitionByFading(final Image nextImage, final ImageView imageView) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), imageView);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished((ActionEvent ae) -> {
            imageView.setImage(nextImage);
        });
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), imageView);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        SequentialTransition seqTransition = new SequentialTransition();
        seqTransition.getChildren().addAll(fadeOut, fadeIn);
        return seqTransition;
    }
}