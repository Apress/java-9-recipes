package org.java9recipes.chapter11.recipe11_06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Freddy
 * Updated: Juneau
 * Recipe 11-6 - Deadlock Example, Use JStack to Monitor
 */
public class Recipe11_6 {
    Lock firstLock = new ReentrantLock();
    Lock secondLock = new ReentrantLock();


    public static void main (String[] args) {
        Recipe11_6 recipe = new Recipe11_6();
        recipe.start();
    }

    private void start() {
        firstLock.lock();
        Thread secondThread = new Thread(() -> {
            secondLock.lock();
            firstLock.lock();
        });

        secondThread.start();
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        secondLock.lock();

        secondLock.unlock();
        firstLock.unlock();

    }

}
