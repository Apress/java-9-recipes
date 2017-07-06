/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java9recipes.chapter01.recipe01_02;

/**
 *
 * @author Juneau
 */
public class HelloWorld {
    /* The main method begins in this class */

    public static void main(String[] args) {
        
        HelloMessage hm;     
        hm = new HelloMessage();
        
        System.out.println(hm.getMessage());
        
        hm.setMessage("Hello, World");
        
        System.out.println(hm.getMessage());    
    }
}
