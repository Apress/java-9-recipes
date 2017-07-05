
package org.java8recipes.chapter18.recipe18_11;

import java.io.FileReader;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Recipe 18-9
 * @author Juneau
 */
public class CarCollector extends Application {

    private final String SCRIPT = getClass().getResource("carCollector.js").getPath();

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.put("primaryStage", stage);
            engine.eval(new FileReader(SCRIPT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
