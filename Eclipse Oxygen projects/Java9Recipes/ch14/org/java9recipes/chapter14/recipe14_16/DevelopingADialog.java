package org.java9recipes.chapter14.recipe14_16;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Recipe 14-16: Developing A Dialog
 * @author cdea
 * Update: J. Juneau
 */
public class DevelopingADialog extends Application {

    static Stage LOGIN_DIALOG;
    static int dx = 1;
    static int dy = 1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    private static Stage createLoginDialog(Stage parent, boolean modal) {
        if (LOGIN_DIALOG != null) {
            LOGIN_DIALOG.close();
        }
        return new MyDialog(parent, modal, "Welcome to JavaFX!");
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Chapter 14-16 Developing a Dialog");
        Group root = new Group();
        Scene scene = new Scene(root, 433, 312, Color.WHITE);

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        
        Menu menu = new Menu("Home");

        // add change password menu itme
        MenuItem newItem = new MenuItem("Change Password", null);
        newItem.setOnAction((ActionEvent event) -> {
            if (LOGIN_DIALOG == null) {
                LOGIN_DIALOG = createLoginDialog(primaryStage, true);
            }
            LOGIN_DIALOG.sizeToScene();
            LOGIN_DIALOG.show();
        });

        menu.getItems().add(newItem);

        // add separator
        menu.getItems().add(new SeparatorMenuItem());

        // add non modal menu item
        ToggleGroup modalGroup = new ToggleGroup();
        RadioMenuItem nonModalItem = new RadioMenuItem();
        nonModalItem.setToggleGroup(modalGroup);
        nonModalItem.setText("Non Modal");
        nonModalItem.setSelected(true);
            
        nonModalItem.setOnAction((ActionEvent event) -> {
            LOGIN_DIALOG = createLoginDialog(primaryStage, false);
        });

        menu.getItems().add(nonModalItem);

        // add modal selection
        RadioMenuItem modalItem = new RadioMenuItem();
        modalItem.setToggleGroup(modalGroup);
        modalItem.setText("Modal");
        modalItem.setSelected(true);
               
        modalItem.setOnAction((ActionEvent event) -> {
            LOGIN_DIALOG = createLoginDialog(primaryStage, true);
        });
        menu.getItems().add(modalItem);

        // add separator
        menu.getItems().add(new SeparatorMenuItem());

        // add exit
        MenuItem exitItem = new MenuItem("Exit", null);
        exitItem.setMnemonicParsing(true);
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        exitItem.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
        menu.getItems().add(exitItem);

        // add menu
        menuBar.getMenus().add(menu);

        // menu bar to window
        root.getChildren().add(menuBar);

        primaryStage.setScene(scene);
        primaryStage.show();

        addBouncyBall(scene);
    }

    private void addBouncyBall(final Scene scene) {

        final Circle ball = new Circle(100, 100, 20);
        RadialGradient gradient1 = new RadialGradient(0,
                .1,
                100,
                100,
                20,
                false,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED),
                new Stop(1, Color.BLACK));

        ball.setFill(gradient1);

        final Group root = (Group) scene.getRoot();
        root.getChildren().add(ball);

        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(.0200), (ActionEvent event) -> {
            double xMin = ball.getBoundsInParent().getMinX();
            double yMin = ball.getBoundsInParent().getMinY();
            double xMax = ball.getBoundsInParent().getMaxX();
            double yMax = ball.getBoundsInParent().getMaxY();
            
            // Collision - boundaries
            if (xMin < 0 || xMax > scene.getWidth()) {
                dx = dx * -1;
            }
            if (yMin < 0 || yMax > scene.getHeight()) {
                dy = dy * -1;
            }
            
            ball.setTranslateX(ball.getTranslateX() + dx);
            ball.setTranslateY(ball.getTranslateY() + dy);
        });

        tl.getKeyFrames().add(moveBall);
        tl.play();
    }
}

class MyDialog extends Stage {

    public MyDialog(Stage owner, boolean modality, String title) {
        super();
        initOwner(owner);
        Modality m = modality ? Modality.APPLICATION_MODAL : Modality.NONE;
        initModality(m);
        setOpacity(.90);
        setTitle(title);
        Group root = new Group();
        Scene scene = new Scene(root, 250, 150, Color.WHITE);
        setScene(scene);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);

        Label mainLabel = new Label("Enter User Name & Password");
        gridpane.add(mainLabel, 1, 0, 2, 1);


        Label userNameLbl = new Label("User Name: ");
        gridpane.add(userNameLbl, 0, 1);

        Label passwordLbl = new Label("Password: ");
        gridpane.add(passwordLbl, 0, 2);


        // username text field
        final TextField userNameFld = new TextField("Admin");
        gridpane.add(userNameFld, 1, 1);

        // password field
        final PasswordField passwordFld = new PasswordField();
        passwordFld.setText("drowssap");
        gridpane.add(passwordFld, 1, 2);


        Button login = new Button("Change");
        login.setOnAction((ActionEvent event) -> {
            close();
        });
        gridpane.add(login, 1, 3);
        GridPane.setHalignment(login, HPos.RIGHT);
        root.getChildren().add(gridpane);
    }
}