package org.java9recipes.chapter11.recipe11_04.src;

/**
 * User: Freddy
 * Updated: Juneau
 * Recipe 11-5: 
 */
public class Recipe11_4 {
    private String testString;

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }
    public static void main(String[] args) {
    	
        Recipe11_4 recipe = new Recipe11_4();
        recipe.setTestString("A string");
        System.out.println("is string 'A string?':"+recipe.testforAString());
    }

    private boolean testforAString() {
        return (testString == "A string");
    }
}
