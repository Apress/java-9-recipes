

package org.java9recipes.chapter18.recipe18_01;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Recipe 18-1:  Loading and Executing JavaScript from Java
 * @author Juneau
 */
public class NashornInvoker {
    
    public static void main(String[] args){
        loadExternalJs();
        loadInlineJs();
        loadInlineJsReturn();
    }
    
    public static void loadExternalJs(){
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine nashorn = sem.getEngineByName("nashorn");
        
        try {
            nashorn.eval("load('js/helloNashorn.js')");
        } catch (ScriptException ex) {
            Logger.getLogger(NashornInvoker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void loadInlineJs(){
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine nashorn = sem.getEngineByName("nashorn");
        try {
            nashorn.eval("function gallons(width, length, avgDepth){var volume = avgDepth * width * length;"
                    + "                                             return volume * 7.48; }");
            nashorn.eval("print('Gallons of water in pool: '+ gallons(16,32,5))");
        } catch (ScriptException ex) {
            Logger.getLogger(NashornInvoker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void loadInlineJsReturn(){
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine nashorn = sem.getEngineByName("nashorn");
        try {
            nashorn.eval("function gallons(width, length, avgDepth){var volume = avgDepth * width * length;"
                    + "                                             return volume * 7.48; }");
            Double gallons = (Double) nashorn.eval("gallons(16,32,5);");
            System.out.println("The number of gallons: " + gallons);
        } catch (ScriptException ex) {
            Logger.getLogger(NashornInvoker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
