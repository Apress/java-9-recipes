
package org.java9recipes.chapter03.recipe3_05;

/**
 * Recipe 3-5
 * 
 * Concatenating Strings
 * 
 * @author juneau
 * @author anatolij
 */
public class StringExamples {
    
    
    
    public static void main(String[] args){

       concatExample();
       concatOperatorExample();
       stringBufferExample();
       
    }
   
    
    public static void concatExample(){
        String one = "Hello";
        String two = "Java9";
        String result = one.concat(" ".concat(two));
        
        System.out.println(result);
    }
    
    public static void concatOperatorExample(){
        String one = "Hello";
        String two = "Java9";
        String result = one + " " + two;
        
        System.out.println(result);
    }
    
    /**
     * 
     * @see <a href="http://download.java.net/java/jdk9/docs/api/java/lang/StringBuffer.html">StringBuffer online documentation</a>
     */
    public static void stringBufferExample(){
        String one = "Hello";
        String two = "Java9";
        StringBuffer buffer = new StringBuffer();
        buffer.append(one).append(" ").append(two);

        String result = buffer.toString();

        System.out.println(result);
    }
}