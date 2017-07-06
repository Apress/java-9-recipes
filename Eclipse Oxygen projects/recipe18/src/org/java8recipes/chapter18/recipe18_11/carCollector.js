var ArrayList = Java.type("java.util.ArrayList");
var Scene = javafx.scene.Scene;
var Button = javafx.scene.control.Button;
var TextField = javafx.scene.control.TextField;
var GridPane = javafx.scene.layout.GridPane;
var Label = javafx.scene.control.Label;
var TextArea = javafx.scene.control.TextArea;

var carList = new ArrayList();
var carCount = "There are currently no cars";
var car = {
    make: "",
    model: "",
    year: "",
    description: ""
};
print(carCount);

primaryStage.title = "Car Form JS Demo";

var grid = new GridPane();
grid.hgap = 10;
grid.vgap = 10;

var makeLabel = new Label("Make:");
grid.add(makeLabel, 0, 1);

var makeText = new TextField();
grid.add(makeText, 1, 1);

var modelLabel = new Label("Model:");
grid.add(modelLabel, 0, 2);

var modelText = new TextField();
grid.add(modelText, 1, 2);

var yearLabel = new Label("Year:");
grid.add(yearLabel, 0, 3);

var yearText = new TextField();
grid.add(yearText, 1, 3);

var descriptionLabel = new Label("Description:");
grid.add(descriptionLabel, 0, 4);

var descriptionText = new TextArea();
grid.add(descriptionText, 1, 4);

var button = new Button("Add Car");
button.onAction = function() {
    print("Adding Car:" + makeText.text);
    car.make = makeText.text;
    car.model = modelText.text;
    car.year = yearText.text;
    car.description = descriptionText.text;
    carList.add(car);
    carCount = "The number of cars is: " + carList.size();
    print(carCount);
};
grid.add(button, 0, 5);

primaryStage.scene = new Scene(grid, 800, 500);
primaryStage.show();


