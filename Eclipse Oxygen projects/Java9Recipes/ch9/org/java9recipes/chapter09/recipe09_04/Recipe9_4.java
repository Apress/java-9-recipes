package org.java9recipes.chapter09.recipe09_04;


import java.io.*;

/**
 * User: Freddy
 * Modified by Juneau
 * Recipe 9-4
 */
public class Recipe9_4 {
    public static void main(String[] args) {
        Recipe9_4 recipe = new Recipe9_4();
        recipe.start();
        recipe.startClassic();
    }

    private void startClassic() {
        try {
            Class<?> stringClass = Class.forName("java.lang.String");stringClass.toString();
            
            try(FileInputStream in = new FileInputStream("myFile.log")){
            	in.read();	
            }; // Can throw IOException
            


        } catch (IOException e) {
            System.out.println("There was an IOException "+e);
        } catch (ClassNotFoundException e) {

            System.out.println("There was a CLassNotFoundException "+e);

        }
    }

    private void start() {

        try {
            Class<?> stringClass = Class.forName("java.lang.String");stringClass.toString();
            try(FileInputStream in = new FileInputStream("myFile.log")){
            	in.read();
            } // Can throw IOException
            
            


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An exception of type "+e.getClass()+" was thrown! "+e);
        }
    }
}
