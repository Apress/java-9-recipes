package org.java9recipes.chapter10.recipe10_11;

import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 * @author Juneau
 * @author anatolij kosorukov
 */
public class Recipe10_11 {

	DoubleAdder da;
	LongAdder la;
	public Recipe10_11() {
		da = new DoubleAdder();
		la = new LongAdder();
		
	    
	}
    

    public static void main(String[] args) {
        Recipe10_11 recipe10_13 = new Recipe10_11();
        recipe10_13.start();
    }

    private void start() {

        final Thread thread1 = new Thread(() -> {
            for (int i1 = 0; i1 < 10; i1++) {
                da.add(i1);
                la.add(i1);
                System.out.println("Adding " + i1 + " "+ Thread.currentThread().getName());
            }
        });
        thread1.setName("th1");

        Thread thread2 = new Thread(() -> {
            for (int i1 = 0; i1 < 10; i1++) {
                da.add(i1);
                la.add(i1);
                
                System.out.println("Adding " + i1 + " "+ Thread.currentThread().getName());
            }
        });
        thread2.setName("th2");
        thread1.start();
        thread2.start();

        try {
            // Sleep while summing
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The sum is: " + da.doubleValue());
        System.out.println("The sum is: " + la.doubleValue());
    }
}
