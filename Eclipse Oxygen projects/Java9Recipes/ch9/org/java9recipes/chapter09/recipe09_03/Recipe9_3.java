package org.java9recipes.chapter09.recipe09_03;

/**
 * User: Freddy
 * Modified by Juneau
 * Recipe 9-3
 */
public class Recipe9_3 {
    public static void main(String[] args) {
        Recipe9_3 recipe = new Recipe9_3();
        recipe.start();
    }

    private void start() {
        try {
            callSomeFunctionThatMightThrow(null);
        } catch (NullPointerException e) {
            System.out.println("There was an null parameter!");
        }

    }

    private void callSomeFunctionThatMightThrow(Object o) {
        if (o == null) throw new NullPointerException("The object is null");

    }
}
