package org.java9recipes.chapter09.recipe09_10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

/**
 * User: Freddy
 * Updated by Juneau
 * Recipe 9-10
 */
public class Recipe9_10 {
    public static void main (String[] args) throws IOException {
        Recipe9_10 recipe = new Recipe9_10();
        recipe.start();
    }

    private void start() {
        loadLoggingConfiguration();

        Logger logger = LoggerFactory.getLogger("");
        logger.info("Logging for the first Time!");
        logger.warn("A warning to be had");
        logger.error("This is an error!");

        Logger rollingLogger = LoggerFactory.getLogger("rollingLogger");
        for (int i =0;i < 5000;i++) {
            rollingLogger.info("Logging for an event with :"+i);
        }
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
