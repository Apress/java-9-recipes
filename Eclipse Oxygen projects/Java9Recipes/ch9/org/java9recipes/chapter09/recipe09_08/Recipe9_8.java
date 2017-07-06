package org.java9recipes.chapter09.recipe09_08;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * User: Freddy
 * Modified by Juneau
 * Recipe 9-8
 * @author anatolij kosorukov
 * 
 */
public class Recipe9_8 {
    public static void main (String[] args) {
        Recipe9_8 recipe = new Recipe9_8();
        recipe.start();
    }

    private void start() {
        try {
            doSomeWork();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doSomeWork() throws IOException, InterruptedException {
    	String fileName = "out.log";
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        for (int i = 0; i < 100; i++) {
        	queue.put("string"+i);
		}
        try (FileOutputStream fos = new FileOutputStream(fileName);DataOutputStream dos = new DataOutputStream(fos);){
            
            
            while (!queue.isEmpty()) {
                dos.writeUTF(queue.take());
            }
            System.out.printf("Queue's size = %d\n",queue.size());
        } catch (InterruptedException | IOException e ) {
            e.printStackTrace();
            throw e;
        }finally {
        	Files.deleteIfExists(Paths.get(fileName));
        }

    }
}
