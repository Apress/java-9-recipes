package org.java9recipes.chapter09.recipe09_06;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * User: Freddy
 * Modified by Juneau
 * Recipe 9-6
 */
public class Recipe9_6 {
    public static void main(String []args) {
        Recipe9_6 recipe = new Recipe9_6();
        recipe.start();
    }

    private void start() {
    	String fileName = "out.log";
        try (
                FileOutputStream fos = new FileOutputStream(fileName);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                DataOutputStream dos = new DataOutputStream(bos)
        ) {
            dos.writeUTF("This is being written");
            // If an exception occurs here, resources will be handled correctly
            dos.close();
        } catch (Exception e) {
            System.out.println("Some bad exception happened ");
        }finally {
        	try {
				Files.deleteIfExists(Paths.get(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
}
