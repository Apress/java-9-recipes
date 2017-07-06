package org.java9recipes.chapter11.recipe11_02;

/**
 * User: Freddy
 * Updated: Juneau
 * Recipe 11-2: Assertions
 */
public class Recipe11_2 {
    public static void main (String[] args) {
        Recipe11_2 recipe = new Recipe11_2();
        recipe.start();
    }


	@SuppressWarnings("unused")
	private void start() {

        assert (true) : "It didn't work";

        assert (false): "false!";

    }
}
