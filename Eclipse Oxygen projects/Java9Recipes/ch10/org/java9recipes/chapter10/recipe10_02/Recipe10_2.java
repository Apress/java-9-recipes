package org.java9recipes.chapter10.recipe10_02;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User: Freddy Updated by Juneau Recipe 10-2
 * 
 * @see <a href=
 *      "http://download.java.net/java/jdk9/docs/api/java/util/concurrent/ConcurrentHashMap.html">ConcurrentHashMap
 *      documentation</a>
 */
public class Recipe10_2 {
	public static void main(String[] args) {
		Recipe10_2 recipe = new Recipe10_2();
		recipe.startProcess();
	}

	Set<Thread> updateThreads = new HashSet<>();

	private void startProcess() {
		ConcurrentMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
		for (int i = 0; i < 1000; i++) {
			startUpdateThread(i, concurrentMap);
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		concurrentMap.entrySet().stream().forEach((entry) -> {
			System.out.println("Key :" + entry.getKey() + " Value:" + entry.getValue());
		});

		updateThreads.stream().forEach((thread) -> {
			thread.interrupt();
		});
	}

	Random random = new Random();

	private void startUpdateThread(int i, final ConcurrentMap<Integer, String> concurrentMap) {
		Thread thread = new Thread(() -> {
			while (!Thread.interrupted()) {
				int randomInt = random.nextInt(20);
				concurrentMap.put(randomInt, UUID.randomUUID().toString());
			}
		});
		thread.setName("Update Thread " + i);
		updateThreads.add(thread);
		thread.start();
	}
}
