package org.java9recipes.chapter15.recipe15_05;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * MyForm is a form to be manipulated by the user.
 * @author cdea
 * Update: J Juneau
 */
public class MyForm extends GridPane{
    public MyForm() {
        
        setPadding(new Insets(5));
        setHgap(5);
        setVgap(5);

        Label fNameLbl = new Label("First Name");
        TextField fNameFld = new TextField();
        Label lNameLbl = new Label("Last Name");
        TextField lNameFld = new TextField();
        Label ageLbl = new Label("Age");
        TextField ageFld = new TextField();

        Button saveButt = new Button("Save");
        
        // First name label
        GridPane.setHalignment(fNameLbl, HPos.RIGHT);
        add(fNameLbl, 0, 0);
        
        // Last name label
        GridPane.setHalignment(lNameLbl, HPos.RIGHT);
        add(lNameLbl, 0, 1);

        // Age label
        GridPane.setHalignment(ageLbl, HPos.RIGHT);
        add(ageLbl, 0, 2);

        // First name field
        GridPane.setHalignment(fNameFld, HPos.LEFT);       
        add(fNameFld, 1, 0);
        
        // Last name field
        GridPane.setHalignment(lNameFld, HPos.LEFT);
        add(lNameFld, 1, 1);

        // Age Field
        GridPane.setHalignment(ageFld, HPos.RIGHT);
        add(ageFld, 1, 2);

        // Save button
        GridPane.setHalignment(saveButt, HPos.RIGHT);
        add(saveButt, 1, 3);
        
    }
}
