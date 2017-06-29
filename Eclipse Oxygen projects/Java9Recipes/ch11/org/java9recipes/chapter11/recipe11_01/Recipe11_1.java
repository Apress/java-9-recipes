package org.java9recipes.chapter11.recipe11_01;

/**
 * User: Freddy
 * Updated: Juneau
 * Recipe 11-1: Exception
 */
public class Recipe11_1 {
    public static void main (String[] args) {
        Recipe11_1 recipe = new Recipe11_1();
        recipe.startProcess();
    }

    private void startProcess() {
        try {
            int a = 5/0;
            System.out.println(a);
        }  catch (Exception e) {
            e.printStackTrace();
           
        }

    }
}
