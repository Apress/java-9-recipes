/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java9recipes.chapter01.recipe01_02;

/**
 * Recipe 1-2: Getting to "Hello World"
 * 
 * @author Juneau
 */
/* An object of this class will hold the message. */
public class HelloMessage {
    private String message = "";
   
    public HelloMessage() {
        this.message = "Default Message";
    }
        
    public void setMessage (String m) {
        this.message = m;
    }
   
    public String getMessage () {
        return this.message.toUpperCase();
    }   
}

