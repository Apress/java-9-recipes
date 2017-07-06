package org.java9recipes.chapter09.recipe09_01;

/**
 * User: Freddy
 * Modified by Juneau
 * Recipe 9-1
 */
public class Recipe9_1 {
    public static void main(String[] args) {
        Recipe9_1 recipe = new Recipe9_1();
        recipe.start();
    }

    private void start() {
        System.out.println("Is th string 1234 longer than 5 chars?:"+
                isStringShorterThanFiveCharacters("1234"));
        System.out.println("Is th string 12345 longer than 5 chars?:"+
                isStringShorterThanFiveCharacters("12345"));
        System.out.println("Is th string 123456 longer than 5 chars?:"+
                isStringShorterThanFiveCharacters("123456"));
        System.out.println("Is th string null longer than 5 chars?:"+
                isStringShorterThanFiveCharacters(null));

    }

    private boolean isStringShorterThanFiveCharacters(String aString) {
        try {
            return aString.length() > 5;
        } catch (NullPointerException e) {
            System.out.println("An Exception Occurred!");
            
            return false;
        }
    }
}
