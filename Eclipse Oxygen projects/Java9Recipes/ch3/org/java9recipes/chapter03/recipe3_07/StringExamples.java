
package org.java9recipes.chapter03.recipe3_07;

/**
 * Recipe 3-7
 * 
 * Iterating Over the Characters of a String
 * 
 * @author juneau
 */
public class StringExamples {    
    
    public static void main(String[] args){

       breakToChars();

    }
      
    public static void breakToChars(){
        String str = "Break down into chars";
        
        System.out.println(str);
        
        for (char chr:str.toCharArray()){
            System.out.println(chr);
        }
        
        for (int x = 0; x <= str.length()-1; x++){
            System.out.println(str.charAt(x));
        }
    }
    
   
}
