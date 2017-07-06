package org.java9recipes.chapter14.recipe14_18;

import javafx.application.Application;

import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;

/**
 *
 * @author Juneau
 */
public class UserEntryForm extends Application {

    private static ToggleButton fxbutton;
    private static GridPane grid;
    public static Label fxLabel;

    @Override
    public void start(Stage stage) {
        final SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);
        BorderPane pane = new BorderPane();
        Image fxButtonIcon = new Image(
                getClass().getResourceAsStream("images/duke1.gif"));
        String buttonText = "Use Swing Form";
        fxbutton = new ToggleButton(buttonText, new ImageView(fxButtonIcon));
        fxbutton.setTooltip(
                new Tooltip("This button chooses between the Swing and FX form"));
        fxbutton.setStyle("-fx-font: 22 arial; -fx-base: #cce6ff;");
        fxbutton.setAlignment(Pos.CENTER);
        fxbutton.setOnAction((event)->{
            ToggleButton toggle = (ToggleButton) event.getSource();
            if(!toggle.isSelected()){
                swingNode.setDisable(true);
                swingNode.setVisible(false);
                grid.setDisable(false);
                grid.setVisible(true);
                fxbutton.setText("Use Swing Form");
            } else {
                swingNode.setDisable(false);
                swingNode.setVisible(true);
                grid.setDisable(true);
                grid.setVisible(false);
                fxbutton.setText("Use JavaFX Form");
            }
        });
        // Disable SwingNode by default
        swingNode.setVisible(false);
        Text appTitle = new Text("Swing/FX Form Demo");
        appTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        pane.setTop(appTitle);
        HBox formPanel = new HBox();
        formPanel.setSpacing(10);
        fxLabel = new Label("Message from JavaFX form...");
    
        formPanel.getChildren().addAll(fxFormContent(), swingNode);
        
        pane.setCenter(formPanel);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(fxbutton, fxLabel);
        
        pane.setBottom(vbox);
        
        Scene scene = new Scene(pane, 700, 500);
        stage.setScene(scene);
        stage.setTitle("Swing Form Embedded In JavaFX");
        stage.show();
    }

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            swingNode.setContent(new SwingForm());
        });
    }

    private GridPane fxFormContent() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Enter User");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label first = new Label("First Name:");
        grid.add(first, 0, 1);

        TextField firstField = new TextField();
        grid.add(firstField, 1, 1);

        Label last = new Label("Last Name:");
        grid.add(last, 0, 2);

        TextField lastField = new TextField();
        grid.add(lastField, 1, 2);
        
        Button messageButton = new Button("Click");
        messageButton.setOnAction((event) ->{
            fxLabel.setText("Message from JavaFX Form...");
        });
        grid.add(messageButton, 0,3);
      
        return grid;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
