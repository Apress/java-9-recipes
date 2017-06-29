package org.java9recipes.chapter10.recipe10_12;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Juneau
 * @author anatolij kosorukov
 */
public class Recipe10_12 {

    public static void main(String[] args) {
        try {
            CompletableFuture<String> tasks = performWork()
                    .thenApply(work -> {
                        String newTask = work + " Second task complete!";
                        System.out.println(newTask);
                        return newTask;
                    }).thenApply(finalTask -> finalTask + " Final Task Complete!");
            
            CompletableFuture<String> future = performSecondWork("Java 9 is Great! ");
            while(!tasks.isDone()){
               System.out.println(future.get());
            }
            System.out.println(tasks.get());
            
            
        } catch (ExecutionException | InterruptedException ex) {

        }
    }

    /**
     * Returns a CompleableFuture object.
     * @return 
     */
    public static CompletableFuture<String> performWork() {
        CompletableFuture<String> resultingWork = CompletableFuture.supplyAsync(
                () -> {
                    String taskMessage = "First task complete!";
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                    System.out.println(taskMessage);
                    return taskMessage;
                });
        return resultingWork;

    }

    /**
     * Accepts a String and returns a CompletableFuture.
     * @param message
     * @return 
     */
    public static CompletableFuture<String> performSecondWork(String message) {
        CompletableFuture<String> resultingWork = CompletableFuture.supplyAsync(
                () -> {
                    String taskMessage = message + " Another task complete!";
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }

                    return taskMessage;
                });
        return resultingWork;

    }
}
