
package org.java8recipes.chapter18.recipe18_04;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * 18-4: Utilizing Java Parameters within JavaScript
 * @author Juneau
 */
public class Recipe18_4 {
    
    public static void main(String[] args){
        loadParameters();
    }
    
    public static void loadParameters(){
        String myJavaString = "This is a Java parameter!";
        float width = 16;
        float length = 32;
        float depth = 5;

        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine nashorn = sem.getEngineByName("nashorn");


        try {
            SimpleBindings simpleBindings = new SimpleBindings(); 
            simpleBindings.put("myString", myJavaString); 
            nashorn.eval("print (myString)", simpleBindings); 

            SimpleBindings simpleBindings2 = new SimpleBindings(); 
            simpleBindings2.put("globalWidth", width);
            simpleBindings2.put("globalLength", length);
            simpleBindings2.put("globalDepth", depth);
            nashorn.eval("function gallons(width, length, avgDepth){var volume = avgDepth * width * length; " +
                         "        return volume * 7.48; }   " +
                    "print(gallons(globalWidth, globalLength, globalDepth));", simpleBindings2);

        } catch (ScriptException ex) {
            Logger.getLogger(Recipe18_4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
