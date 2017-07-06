package org.java9recipes.chapter09.recipe09_09;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Freddy
 * Updated by Juneau
 * Recipe 9-9
 */
public class Recipe9_9 {
    public static void main (String[] args) {
        Recipe9_9 recipe = new Recipe9_9();
        recipe.start();
    }

    private void start() {
        loadLoggingConfiguration();
        Logger logger = LoggerFactory.getLogger("recipeLogger");
        logger.info("Logging for the first Time!");
        logger.warn("A warning to be had");
        logger.error("This is an error!");
    }

    private void loadLoggingConfiguration() {
        FileInputStream ins = null;
        try {
            ins = new FileInputStream(new File("logging.properties"));
            LogManager.getLogManager().readConfiguration(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
