package org.java9recipes.chapter14.recipe14_13;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Working with Tables
 *
 * @author cdea Update: J. Juneau
 */
public class WorkingWithTables extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(WorkingWithTables.class, args);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 14-13 Working with Tables");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 250, Color.WHITE);

        // create a grid pane
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        // candidates label
        Label candidatesLbl = new Label("Boss");
        GridPane.setHalignment(candidatesLbl, HPos.CENTER);
        gridpane.add(candidatesLbl, 0, 0);

        // List of leaders
        ObservableList<Person> leaders = getPeople();
        final ListView<Person> leaderListView = new ListView<>(leaders);
        leaderListView.setPrefWidth(150);
        leaderListView.setPrefHeight(150);

        // display first and last name with tooltip using alias
        leaderListView.setCellFactory((ListView<Person> param) -> {
            final Label leadLbl = new Label();
            final Tooltip tooltip = new Tooltip();
            final ListCell<Person> cell = new ListCell<Person>() {
                @Override
                public void updateItem(Person item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        leadLbl.setText(item.getAliasName());
                        setText(item.getFirstName() + " " + item.getLastName());
                        tooltip.setText(item.getAliasName());
                        setTooltip(tooltip);
                    }
                }
            }; // ListCell
            return cell;
        }); // setCellFactory

        gridpane.add(leaderListView, 0, 1);

        Label emplLbl = new Label("Employees");
        gridpane.add(emplLbl, 2, 0);
        GridPane.setHalignment(emplLbl, HPos.CENTER);

        final TableView<Person> employeeTableView = new TableView<>();
        employeeTableView.setPrefWidth(300);

        final ObservableList<Person> teamMembers = FXCollections.observableArrayList();
        employeeTableView.setItems(teamMembers);

        TableColumn<Person, String> aliasNameCol = new TableColumn<Person, String>("Alias");
        aliasNameCol.setEditable(true);
        aliasNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("aliasName"));

        aliasNameCol.setPrefWidth(employeeTableView.getPrefWidth() / 3);

        TableColumn<Person, String> firstNameCol = new TableColumn<Person, String>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        firstNameCol.setPrefWidth(employeeTableView.getPrefWidth() / 3);

        TableColumn<Person, String> lastNameCol = new TableColumn<Person, String>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        lastNameCol.setPrefWidth(employeeTableView.getPrefWidth() / 3);

        employeeTableView.getColumns().setAll(aliasNameCol, firstNameCol, lastNameCol);
        gridpane.add(employeeTableView, 2, 1);

        // selection listening
        leaderListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Person> observable, Person oldValue, Person newValue) -> {
            if (observable != null && observable.getValue() != null) {
                teamMembers.clear();
                teamMembers.addAll(observable.getValue().employeesProperty());
            }
        });

        root.getChildren().add(gridpane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ObservableList<Person> getPeople() {
        ObservableList<Person> people = FXCollections.<Person>observableArrayList();
        Person docX = new Person("Professor X", "Charles", "Xavier");
        docX.employeesProperty().add(new Person("Wolverine", "James", "Howlett"));
        docX.employeesProperty().add(new Person("Cyclops", "Scott", "Summers"));
        docX.employeesProperty().add(new Person("Storm", "Ororo", "Munroe"));

        Person magneto = new Person("Magneto", "Max", "Eisenhardt");
        magneto.employeesProperty().add(new Person("Juggernaut", "Cain", "Marko"));
        magneto.employeesProperty().add(new Person("Mystique", "Raven", "Darkhölme"));
        magneto.employeesProperty().add(new Person("Sabretooth", "Victor", "Creed"));

        Person biker = new Person("Mountain Biker", "Jonathan", "Gennick");
        biker.employeesProperty().add(new Person("JavaJuneau", "Joshua", "Juneau"));
        biker.employeesProperty().add(new Person("Freddy", "Freddy", "Guime"));
        biker.employeesProperty().add(new Person("Mark", "Mark", "Beaty"));
        biker.employeesProperty().add(new Person("John", "John", "O'Conner"));
        biker.employeesProperty().add(new Person("D-Man", "Carl", "Dea"));

        people.add(docX);
        people.add(magneto);
        people.add(biker);

        return people;
    }
}
