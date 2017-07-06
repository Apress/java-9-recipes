package org.java9recipes.chapter11.recipe11_05;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * User: Freddy
 * Updated: Juneau
 * Recipe 11-6:
 */
public class Recipe11_5 {
    Map<String, Integer> leakyMap = new HashMap<>();
    
    public static void main(String[] args) {
        Recipe11_5 recipe = new Recipe11_5();
        recipe.start();
    }

    private void start() {

        for (int i= 0;i < 5000;i++) {
            leakyMap.put(UUID.randomUUID().toString(),i);
                for (int j = 0;j < 10000;j++ ) {
                   // String stringThatConsumes = 
                    		new String("a"+i);
                   
                }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i % 20 == 0) System.out.println("Writing :"+i);
        }
    }
}
