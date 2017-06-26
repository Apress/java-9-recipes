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
public class DynamicStringPrint {
    public static void main(String[] args){
        double temperature = 98.6;
        System.out.printf("The current temperature is %.1f degrees Farenheit.\n", temperature);

        temperature = 101.2;
        
        System.out.printf("The current temperature is %.1f degrees Farenheit.", temperature);
        
    }
}
