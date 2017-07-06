
package org.java9recipes.chapter05.recipe5_03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Recipe 5-3
 * 
 * Test class for the enum singleton
 * 
 * @author juneau
 * @author anatolij kosorukov
 */
public class TestSingleton {
	public static void main(String[] args) {
		StatisticsSingleton stats = StatisticsSingleton.INSTANCE;

		System.out.println("Adding objects to the list using stats object");

		List<Object> mylist = stats.getTeams();
		mylist.add("One");
		mylist.add("Two");

		System.out.println("Reading objects from the list using stats2 object");
		StatisticsSingleton stats2 = StatisticsSingleton.INSTANCE;
		List<Object> mylist2 = stats2.getTeams();
		for (Object name : mylist2) {
			System.out.println(name.toString());
		}

		compareSavedIntances(stats, stats2);
	}

	private static void compareSavedIntances(StatisticsSingleton... singletons) {
		File file = new File("recipe5_03_stats");

		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		try (ObjectOutputStream ous1 = new ObjectOutputStream(new FileOutputStream(file))) {

			StatisticsSingleton stats = StatisticsSingleton.INSTANCE;
			ous1.writeObject(stats);	
			
			try (ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(file))) {

				try {
					StatisticsSingleton stat1 = (StatisticsSingleton) ois1.readObject();
					List<Object> list1;
					List<Object> list2;
					for (StatisticsSingleton statisticsSingleton : singletons) {
						if (statisticsSingleton != stat1) {
							throw new IllegalStateException();
						}
						list1 =statisticsSingleton.getTeams();
						list2 = stat1.getTeams();
						if(!list1.equals(list2)){
							throw new IllegalStateException("Lists are not equals");
						}
					}

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
