
package org.java8recipes.chapter18.recipe18_06;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Recipe 18-6:  Utilization of Java Classes and Libraries
 * @author Juneau
 */
public class Recipe18_6 {
    
    public static void main(String[] args){
        loadAndRun();
    }
    
    public static void loadAndRun(){
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine nashorn = sem.getEngineByName("nashorn");
        try {
            nashorn.eval("load('recipe18_06/js/employeeFactory.js');");
        } catch (ScriptException ex) {
            Logger.getLogger(Recipe18_6.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
