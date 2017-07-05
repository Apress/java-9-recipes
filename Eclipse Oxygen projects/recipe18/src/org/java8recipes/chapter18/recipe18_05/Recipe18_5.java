/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java8recipes.chapter18.recipe18_05;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Juneau
 */
public class Recipe18_5 {

    public static void main(String[] args) {

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        // JavaScript code in a String
        String gallonsFunction = "function gallons(width, length, avgDepth){var volume = avgDepth * width * length; "
                + " return volume * 7.48; } ";
        try {
            // evaluate script
            engine.eval(gallonsFunction);
            double width = 16.0;
            double length = 32.0;
            double depth = 5.0;
            Invocable inv = (Invocable) engine;
            double returnValue = (double) inv.invokeFunction("gallons",
                                          new Object[]{width,length,depth});
            System.out.println("The returned value:" + returnValue);
            
            // Invoke a script
            engine.eval("load('recipe18_5/js/recipe18_5.js')");
            Invocable inv2 = (Invocable) engine;
            String returnValue2 = (String) inv2.invokeFunction("returnName", new Object[]{"Nashorn"});
            System.out.println("The returned value:" + returnValue2);

        } catch (ScriptException | NoSuchMethodException ex) {
            Logger.getLogger(Recipe18_5.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
