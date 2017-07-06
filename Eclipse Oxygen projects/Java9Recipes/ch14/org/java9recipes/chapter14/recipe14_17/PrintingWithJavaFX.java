
package org.java9recipes.chapter14.recipe14_17;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Recipe 14-17:  Printing with JavaFX
 * @author Juneau
 */
public class PrintingWithJavaFX extends Application {

    static Stage PRINT_DIALOG;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(PrintingWithJavaFX.class, args);
    }

    private static Stage createPrintDialog(Stage parent, boolean modal, Canvas node) {
        if (PRINT_DIALOG != null) {
            PRINT_DIALOG.close();
        }
        // Create a copy of the current canvas
        WritableImage wim = new WritableImage(300, 300);
        node.snapshot(null, wim);
        ImageView iv = new ImageView();
        iv.setImage(wim);
        return new PrintDialog(parent, modal, "Printing Menu", iv);
    }


    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        Canvas canvas = new Canvas(300, 300);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        final Button printButton = new Button("Print");
        final BooleanProperty printingProperty = new SimpleBooleanProperty(false);
        printButton.setOnAction(actionEvent-> {
           printingProperty.set(true);
           if (PRINT_DIALOG == null) {
                PRINT_DIALOG = createPrintDialog(primaryStage, true, canvas);
            }
            PRINT_DIALOG.sizeToScene();
            PRINT_DIALOG.show();
        });
        printButton.setTranslateX(3);

        final Button resetButton = new Button("Reset");
        resetButton.setOnAction(actionEvent-> {
            graphicsContext.clearRect(1, 1, 
                    graphicsContext.getCanvas().getWidth()-2, 
                    graphicsContext.getCanvas().getHeight()-2);
        });
        resetButton.setTranslateX(10);

        // Set up the pen color chooser
        ChoiceBox<String> colorChooser = new ChoiceBox<>(FXCollections.observableArrayList(
            "Black", "Blue", "Red", "Green", "Brown", "Orange")
        );
        // Select the first option by default
        colorChooser.getSelectionModel().selectFirst();

        colorChooser.getSelectionModel().selectedIndexProperty().addListener(
                (ChangeListener<Number>)(ov, old, newval) -> {
                        Number idx = (Number)newval;
                        Color newColor;
                        switch(idx.intValue()){
                            case 0: newColor = Color.BLACK;
                                    break;
                            case 1: newColor = Color.BLUE;
                                    break;
                            case 2: newColor = Color.RED;
                                    break;
                            case 3: newColor = Color.GREEN;
                                    break;
                            case 4: newColor = Color.BROWN;
                                    break;
                            case 5: newColor = Color.ORANGE;
                                    break;
                            default: newColor = Color.BLACK;
                                    break;
                        }
                        graphicsContext.setStroke(newColor);

                });
        colorChooser.setTranslateX(5);

        ChoiceBox<String> sizeChooser = new ChoiceBox<>(FXCollections.observableArrayList(
            "1", "2", "3", "4", "5")
        );
        // Select the first option by default
        sizeChooser.getSelectionModel().selectFirst();

        sizeChooser.getSelectionModel().selectedIndexProperty().addListener(
                (ChangeListener<Number>)(ov, old, newval) -> {
                        Number idx = (Number)newval;

                        switch(idx.intValue()){
                            case 0: graphicsContext.setLineWidth(1);
                                    break;
                            case 1: graphicsContext.setLineWidth(2);
                                    break;
                            case 2: graphicsContext.setLineWidth(3);
                                    break;
                            case 3: graphicsContext.setLineWidth(4);
                                    break;
                            case 4: graphicsContext.setLineWidth(5);
                                    break;
                            default: graphicsContext.setLineWidth(1);
                                    break;
                        }
                });
        sizeChooser.setTranslateX(5);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            graphicsContext.beginPath();
            graphicsContext.moveTo(event.getX(), event.getY());
            graphicsContext.stroke();
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            graphicsContext.lineTo(event.getX(), event.getY());
            graphicsContext.stroke();
        });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent event) -> {
        });

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(printButton, colorChooser, sizeChooser, resetButton);

        initDraw(graphicsContext, canvas.getLayoutX(), canvas.getLayoutY());

        BorderPane container = new BorderPane();
        container.setTop(buttonBox);

        container.setCenter(canvas);

        root.getChildren().add(container);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Recipe 14-17:  Printing from JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void initDraw(GraphicsContext gc, double x, double y){
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.fill();
        gc.strokeRect(
                x,              //x of the upper left corner
                y,              //y of the upper left corner
                canvasWidth,    //width of the rectangle
                canvasHeight);  //height of the rectangle

        //gc.setFill(Color.RED);
        //gc.setStroke(Color.BLUE);
        //gc.setLineWidth(1);

    }


}

class PrintDialog extends Stage {


	public PrintDialog(Stage owner, boolean modality, String title, Node printNode) {
        super();
        initOwner(owner);
        Modality m = modality ? Modality.APPLICATION_MODAL : Modality.NONE;
        initModality(m);
        setOpacity(.90);
        setTitle(title);
        Group root = new Group();
        Scene scene = new Scene(root, 450, 150, Color.WHITE);
        setScene(scene);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);


        Label printerLabel = new Label("Printer: ");
        gridpane.add(printerLabel, 0, 1);

        Label layoutLabel = new Label("Layout: ");
        gridpane.add(layoutLabel, 0, 2);

        final Printer selectedPrinter = Printer.getDefaultPrinter();
        // printer pick list
        ChoiceBox<Printer> printerChooser = new ChoiceBox<>(FXCollections.observableArrayList(
            Printer.getAllPrinters())
        );
        // Select the first option by default
        printerChooser.getSelectionModel().selectFirst();

        gridpane.add(printerChooser, 1, 1);

        ChoiceBox<String> layoutChooser = new ChoiceBox<>(FXCollections.observableArrayList(
            "Portait", "Landscape")
        );
        layoutChooser.getSelectionModel().selectFirst();

        layoutChooser.getSelectionModel().selectedIndexProperty().addListener(
                (ChangeListener<Number>)(ov, old, newval) -> {
                        Number idx = (Number)newval;                    
                        switch(idx.intValue()){
                            case 0: selectedPrinter.createPageLayout(Paper.A0, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);
                                    break;
                            case 1: selectedPrinter.createPageLayout(Paper.A0, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL);
                                    break;

                            default: selectedPrinter.createPageLayout(Paper.A0, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);
                                    break;
                        }
                });
        gridpane.add(layoutChooser,1,2);
        Button printButton = new Button("Print");
        printButton.setOnAction((ActionEvent event) -> {
            print(printNode, selectedPrinter);
        });
        gridpane.add(printButton, 0, 3);

        GridPane.setHalignment(printButton, HPos.RIGHT);
        root.getChildren().add(gridpane);
    }
    
    public void print(final Node node, Printer printer) {
       
        PrinterJob job = PrinterJob.createPrinterJob();
        job.setPrinter(printer);
        if (job != null) {
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
        }
    }    
}

    