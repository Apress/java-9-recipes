
package org.java9recipes.chapter06.recipe6_07;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Recipe 6-7: Replacing Anonymous Inner Classes
 * 
 * @author Juneau
 */
public class TeamEntryForm extends Application {

	List<Player> team = new ArrayList<>();
	public static final ObservableList<Object> positions = FXCollections.observableArrayList();
	public static final ObservableList<Object> data = FXCollections.observableArrayList();
	public static String GOALIE = "GOALIE";
	public static String CENTER = "CENTER";
	public static String DEFENSE = "DEFENSE";
	public static String LEFTWING = "LEFT";
	public static String RIGHTWING = "RIGHT";

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Utility method for creating a Player and adding the Player to the team List.
	 *
	 * @param first
	 * @param last
	 * @param goals
	 * @param position
	 * @param status
	 */
	private void createPlayer(String first, String last, int goals, String position, int status) {
		Player player = new Player();
		player.setFirstName(first);
		player.setLastName(last);
		player.setGoals(goals);
		player.setPosition(position);
		player.setStatus(status);
		team.add(player);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Team Entry Form");

		// Populate the ListView with Positions
		final ListView<Object> listView = new ListView<>(data);
		listView.setPrefSize(200, 250);
		listView.setEditable(true);

		positions.addAll(GOALIE, CENTER, DEFENSE, LEFTWING, RIGHTWING);
		
		listView.setItems(positions);
		// listView.setCellFactory(ComboBoxListCell.forListView(positions));
		
		// Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		final Label message = new Label();
		message.setText("Enter Player Information and Submit");
		GridPane.setConstraints(message, 0, 0);
		grid.getChildren().add(message);

		final TextField firstName = new TextField();
		firstName.setPromptText("Enter your first name.");
		firstName.setPrefColumnCount(10);

		GridPane.setConstraints(firstName, 0, 1);
		grid.getChildren().add(firstName);

		final TextField lastName = new TextField();
		lastName.setPromptText("Enter your last name.");
		GridPane.setConstraints(lastName, 0, 2);
		grid.getChildren().add(lastName);

		final TextField goals = new TextField();
		goals.setPrefColumnCount(15);
		goals.setPromptText("Number of Goals.");

		GridPane.setConstraints(goals, 0, 3);
		grid.getChildren().add(goals);
		

		GridPane.setConstraints(listView, 0, 4);
		grid.getChildren().add(listView);

		Button btn = new Button();
		btn.setText("Enter Player");
		btn.setDisable(true);
		btn.setOnAction(e -> {

			MultipleSelectionModel<Object> selectionModel = listView.getSelectionModel();
			Object selectedItem = selectionModel.getSelectedItem();
			createPlayer(firstName.getText(), lastName.getText(), Integer.valueOf(goals.getText()),
					selectedItem.toString(), 0);
			message.setText("Player Successfully Added");
			System.out.println("Player added.");
			System.out.println("== Current Player List==");
			for (Player p : team) {
				System.out.println(p.getFirstName() + " " + p.getLastName());
			}
		});

		listView.setOnMouseReleased(ev -> {

			try {
				Integer.parseInt(goals.getText());
				String firstNameValue = firstName.getText();
				if (null != firstNameValue && !"".equals(firstNameValue.trim())) {
					String lastNameValue = lastName.getText();
					if (null != lastNameValue && !"".equals(lastNameValue.trim())) {
						btn.setDisable(false);
					}
				}

			} catch (NumberFormatException e1) {

				// e1.printStackTrace();
			}

		});
	
		
		
		GridPane.setConstraints(btn, 0, 5);
		grid.getChildren().add(btn);

		StackPane root = new StackPane();
		root.getChildren().add(grid);

		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
}
