

package org.java9recipes.chapter14.recipe14_18;

import java.awt.GridLayout;
import javafx.application.Platform;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Juneau
 */
public class SwingForm extends JPanel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7257470351383363325L;
	JLabel formTitle, first, last, buttonLbl;
    protected JTextField firstField, lastField;
    
    public SwingForm(){

    JPanel innerPanel = new JPanel();
    
    GridLayout gl = new GridLayout(3,2);
    innerPanel.setLayout(gl);
    
    first = new JLabel("First Name:");
    innerPanel.add(first);
    firstField = new JTextField(10);
    innerPanel.add(firstField);

    last = new JLabel("Last Name:");
    innerPanel.add(last);
    lastField = new JTextField(10);
    innerPanel.add(lastField);
    
    JButton button = new JButton("Submit");
    button.addActionListener((event) -> {
        Platform.runLater(()-> {
            UserEntryForm.fxLabel.setText("Message from Swing form...");
        });
    });
    buttonLbl = new JLabel("Click Me:");
    innerPanel.add(buttonLbl);
    innerPanel.add(button);
    add(innerPanel);

    }
}