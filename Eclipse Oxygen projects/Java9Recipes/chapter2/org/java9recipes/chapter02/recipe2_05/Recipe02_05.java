

package org.java9recipes.chapter02.recipe2_05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Recipe 2-5:  Filtering Data Before and After a Condition with Streams
 * @author Juneau
 */
public class Recipe02_05 {
    
    public static void main(String[] args){
        List<String> myLangs = Arrays.asList("Jython is great","Groovy is awesome","Scala is functional",
                "JRuby is productive","Java is streamlined","","Kotlin is interesting");
        System.out.format("Collection Data: %s", myLangs);
        
        takeWhileExample();
        dropWhileExample();
    }
    
    
    
    public static void takeWhileExample(){
        Stream.of("Jython is great","Groovy is awesome","Scala is functional",
                "JRuby is productive","Java is streamlined","","Kotlin is interesting")
                .takeWhile(s -> !s.contains("Java"))
                .forEach(System.out::println);
       
    }
    
    public static void dropWhileExample(){
        Stream.of("Jython is great","Groovy is awesome","Scala is functional",
                "JRuby is productive","Java is streamlined","","Kotlin is interesting")
                .dropWhile(s -> !s.contains("Java"))
                .forEach(System.out::println);
       
    }
}
