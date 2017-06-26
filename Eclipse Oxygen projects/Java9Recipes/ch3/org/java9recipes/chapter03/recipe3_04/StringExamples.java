
package org.java9recipes.chapter03.recipe3_04;

import java.util.Locale;

/**
 * Recipe 3-4
 * 
 * Changing the Case of a String
 * 
 * @author juneau
 * @author anatolij
 */
public class StringExamples {
    
    
    
    public static void main(String[] args){
        changeCase();
    }
    
    public static void changeCase(){
        String str = "This String will change case.";
        
        System.out.println(str.toUpperCase());
        System.out.println(str.toLowerCase());
        System.out.println(str.toUpperCase(Locale.ITALIAN));
        System.out.println(str.toUpperCase(new Locale("it","US")));
        System.out.println(str.toLowerCase(new Locale("fr", "CA")));
        
        //compare strings with different locale
        System.out.println(str.toUpperCase(Locale.ITALIAN).equals(str.toUpperCase()));
        System.out.println(str.toUpperCase(new Locale("it","US")).equals(str.toUpperCase()));
        System.out.println(str.toLowerCase(new Locale("fr", "CA")).equals(str.toLowerCase()));
        
    }
    
 }