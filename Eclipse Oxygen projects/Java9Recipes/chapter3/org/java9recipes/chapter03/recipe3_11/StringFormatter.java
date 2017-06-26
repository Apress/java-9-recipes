/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java9recipes.chapter03.recipe3_11;

/**
 *
 * @author Juneau
 */
public class StringFormatter {
    
    
    public static void main(String[] args){
        double temperature = 98.6;
        String temperatureString = "The current temperature is %.1f degrees Farenheit.";

        String newString = String.format(temperatureString, temperature);
        System.out.println(newString);

        temperature = 101.2;
        
        System.out.println(String.format(temperatureString, temperature));
        
    }
}
