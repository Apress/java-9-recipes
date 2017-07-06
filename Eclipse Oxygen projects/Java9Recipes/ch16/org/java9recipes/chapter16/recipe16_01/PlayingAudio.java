package org.java9recipes.chapter16.recipe16_01;

import java.io.File;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Recipe 16-1: Playing Audio
 *
 * @author cdea
 * Update: J Juneau
 */
public class PlayingAudio extends Application {

    private MediaPlayer mediaPlayer;
    private Point2D anchorPt;
    private Point2D previousLocation;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Chapter 16-1 Playing Audio");
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        Group root = new Group();
        Scene scene = new Scene(root, 551, 270, Color.rgb(0, 0, 0, 0));
        
        // application area
        Rectangle applicationArea = new Rectangle();
        applicationArea.setArcWidth(20);
        applicationArea.setArcHeight(20);
        applicationArea.setFill(Color.rgb(0, 0, 0, .80));
        applicationArea.setX(0);
        applicationArea.setY(0);
        applicationArea.setStrokeWidth(2);
        applicationArea.setStroke(Color.rgb(255, 255, 255, .70));

        root.getChildren().add(applicationArea);
        applicationArea.widthProperty().bind(scene.widthProperty());
        applicationArea.heightProperty().bind(scene.heightProperty());

        final Group phaseNodes = new Group();
        root.getChildren().add(phaseNodes);

        // starting initial anchor point
        scene.setOnMousePressed((MouseEvent event) -> {
            anchorPt = new Point2D(event.getScreenX(), event.getScreenY());
        });

        // dragging the entire stage
        scene.setOnMouseDragged((MouseEvent event) -> {
            if (anchorPt != null && previousLocation != null) {
                primaryStage.setX(previousLocation.getX() + event.getScreenX() - anchorPt.getX());
                primaryStage.setY(previousLocation.getY() + event.getScreenY() - anchorPt.getY());
            }
        });

        // set the current location
        scene.setOnMouseReleased((MouseEvent event) -> {
            previousLocation = new Point2D(primaryStage.getX(), primaryStage.getY());
        });

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
                }
                // play file
                Media media = new Media(new File(filePath).toURI().toString());

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }

                mediaPlayer = new MediaPlayer(media);

                // Maintained Inner Class for Tutorial, could be changed to lambda
                mediaPlayer.setAudioSpectrumListener(new AudioSpectrumListener() {
                    @Override
                    public void spectrumDataUpdate(double timestamp, double duration, float[] magnitudes, float[] phases) {
                        phaseNodes.getChildren().clear();
                        int i = 0;
                        int x = 10;
                        int y = 150;
                        final Random rand = new Random(System.currentTimeMillis());
                        for (float phase : phases) {
                            int red = rand.nextInt(255);
                            int green = rand.nextInt(255);
                            int blue = rand.nextInt(255);

                            Circle circle = new Circle(10);
                            circle.setCenterX(x + i);
                            circle.setCenterY(y + (phase * 100));
                            circle.setFill(Color.rgb(red, green, blue, .70));
                            phaseNodes.getChildren().add(circle);
                            i += 5;
                        }
                    }
                });

                mediaPlayer.setOnReady(mediaPlayer::play);
            }

            event.setDropCompleted(success);
            event.consume();
        });

        // create slide controls
        final Group buttonGroup = new Group();

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
        // stop audio control
        Rectangle stopButton = new Rectangle();
        stopButton.setArcWidth(5);
        stopButton.setArcHeight(5);
        stopButton.setFill(Color.rgb(255, 255, 255, .80));
        stopButton.setX(0);
        stopButton.setY(0);
        stopButton.setWidth(10);
        stopButton.setHeight(10);
        stopButton.setTranslateX(15);
        stopButton.setTranslateY(10);
        stopButton.setStroke(Color.rgb(255, 255, 255, .70));
                
        stopButton.setOnMousePressed((MouseEvent me) -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
        });
        buttonGroup.getChildren().add(stopButton);

        // play control
        final Arc playButton = new Arc();
        playButton.setType(ArcType.ROUND);
        playButton.setCenterX(12);
        playButton.setCenterY(16);
        playButton.setRadiusX(15);
        playButton.setRadiusY(15);
        playButton.setStartAngle(180 - 30);
        playButton.setLength(60);
        playButton.setFill(new Color(1, 1, 1, .90));
        playButton.setTranslateX(40);
               
        playButton.setOnMousePressed((MouseEvent me) -> {
            mediaPlayer.play();
        });

        // pause control
        final Group pause = new Group();
        final Circle pauseButton = new Circle();
        pauseButton.setCenterX(12);
        pauseButton.setCenterY(16);
        pauseButton.setRadius(10);
        pauseButton.setStroke(new Color(1, 1, 1, .90));
        pauseButton.setTranslateX(30);
               
        final Line firstLine = new Line();
        firstLine.setStartX(6);
        firstLine.setStartY(16 - 10);
        firstLine.setEndX(6);
        firstLine.setEndY(16 - 2);
        firstLine.setStrokeWidth(3);
        firstLine.setTranslateX(34);
        firstLine.setTranslateY(6);
        firstLine.setStroke(new Color(1, 1, 1, .90));

        final Line secondLine = new Line();
        secondLine.setStartX(6);
        secondLine.setStartY(16 - 10);
        secondLine.setEndX(6);
        secondLine.setEndY(16 - 2);
        secondLine.setStrokeWidth(3);
        secondLine.setTranslateX(38);
        secondLine.setTranslateY(6);
        secondLine.setStroke(new Color(1, 1, 1, .90));
                
        pause.getChildren().addAll(pauseButton, firstLine, secondLine);

        pause.setOnMousePressed((MouseEvent me) -> {
            if (mediaPlayer != null) {
                buttonGroup.getChildren().remove(pause);
                buttonGroup.getChildren().add(playButton);
                mediaPlayer.pause();
            }
        });

        playButton.setOnMousePressed((MouseEvent me) -> {
            if (mediaPlayer != null) {
                buttonGroup.getChildren().remove(playButton);
                buttonGroup.getChildren().add(pause);
                mediaPlayer.play();
            }
        });

        buttonGroup.getChildren().add(pause);
        // move button group when scene is resized
        buttonGroup.translateXProperty().bind(scene.widthProperty().subtract(buttonArea.getWidth() + 6));
        buttonGroup.translateYProperty().bind(scene.heightProperty().subtract(buttonArea.getHeight() + 6));
        root.getChildren().add(buttonGroup);

        // close button
        final Group closeApp = new Group();
        Circle closeButton = new Circle();
        closeButton.setCenterX(5);
        closeButton.setCenterY(0);
        closeButton.setRadius(7);
        closeButton.setFill(Color.rgb(255, 255, 255, .80));
                
        Node closeXmark = new Text(2, 4, "X");
        closeApp.translateXProperty().bind(scene.widthProperty().subtract(15));
        closeApp.setTranslateY(10);
        closeApp.getChildren().addAll(closeButton, closeXmark);
        closeApp.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
        });

        root.getChildren().add(closeApp);

        primaryStage.setScene(scene);
        primaryStage.show();
        previousLocation = new Point2D(primaryStage.getX(), primaryStage.getY());

    }
}
