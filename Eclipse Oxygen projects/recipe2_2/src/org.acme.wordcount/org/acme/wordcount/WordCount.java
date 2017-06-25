/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acme.wordcount;

/**
 *
 * @author Juneau
 */
public class WordCount {

    public static void main(String[] args) {
        int counter = 0;
        if (args.length > 0){
            for(String arg:args){
                System.out.format("Position %d : %d\n", counter, arg.length());
                counter++;
            }
        }
    }
    
}
