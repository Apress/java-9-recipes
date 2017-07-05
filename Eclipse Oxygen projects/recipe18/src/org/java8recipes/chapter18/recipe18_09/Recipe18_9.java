/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.java8recipes.chapter18.recipe18_09;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Juneau
 */
public class Recipe18_9 {
    public static void main(String[] args){
        loadAndRun();
    }
    
    public static void loadAndRun(){
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine nashorn = sem.getEngineByName("nashorn");
        try {
            nashorn.eval("load('recipe18_09/js/recipe18_9.js');");
        } catch (ScriptException ex) {
        	//FIXME:
//        	Exception in thread "main" java.lang.invoke.WrongMethodTypeException: cannot convert MethodHandle(ScriptFunction,Object,Object,Object)int to (ScriptFunction,Object,Object,Object)BigDecimal
            
        	Logger.getLogger(Recipe18_9.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
