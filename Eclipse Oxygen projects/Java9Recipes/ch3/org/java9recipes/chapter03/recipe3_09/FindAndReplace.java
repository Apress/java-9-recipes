
package org.java9recipes.chapter03.recipe3_09;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Recipe 3-9
 * 
 * Replacing All Text Matches
 * 
 * @author juneau
 * @author anatolij
 */
public class FindAndReplace {
    
    public static void main(String[] args) {
        findAndReplaceWithPatterns();
    }
    
    
    public static void findAndReplaceWithPatterns() {

        String str = "I love Java 8!  It is my favorite language.  Java 8 is the "
                + "8th version of this great programming language.";

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(str);

        System.out.println("Original: " + str);
        System.out.println(matcher.matches());
        System.out.println("Replacement: " + matcher.replaceAll("9"));



    }
    
    
}
