

package org.java8recipes.chapter18.recipe18_08;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 18-8:  Implementing Java Interfaces
 * @author Juneau
 */
public class Recipe18_8 {
    public static void main(String[] args){
        loadAndRun();
    }
    
    public static void loadAndRun(){
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine nashorn = sem.getEngineByName("nashorn");
        try {
            nashorn.eval("load('recipe18_08/js/recipe18_8.js');");
        } catch (ScriptException ex) {
            Logger.getLogger(Recipe18_8.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
