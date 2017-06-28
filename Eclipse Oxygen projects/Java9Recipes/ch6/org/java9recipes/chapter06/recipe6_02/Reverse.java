package org.java9recipes.chapter06.recipe6_02;

import java.util.function.Function;

/**
 * Recipe 6-2: Enabling the Use of Lambda Expressions
 *
 * @author Juneau
 */
public class Reverse {

    @FunctionalInterface
    interface ReverseType {

        String reverse(String text);
    }

    public static void main(String[] args) {
        ReverseType newText = (testText) -> {
            String tempStr = "";
            for (String part : testText.split(" ")) {
                tempStr += new StringBuilder(part).reverse().toString() + " ";
            }
            return tempStr;
        };
        
        Function<String,String> newText2 = (testText) -> {
            String tempStr = "";
            for (String part : testText.split(" ")) {
                tempStr += new StringBuilder(part).reverse().toString() + " ";
            }
            return tempStr;
        };

        System.out.println(newText.reverse("HELLO WORLD"));
        System.out.println(newText2.apply("HELLO WORLD"));
    }
}
