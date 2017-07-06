
package org.java9recipes.chapter06.recipe6_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Recipe 6-3: Sorting with Fewer Lines of Code
 * 
 * @author Juneau
 * @author anatolij kosorukov
 */
public class Sorter {

	static List<Player> team;

	private static void loadTeam() {
		System.out.println("Loading team...");
		team = new ArrayList<>();
		Player player1 = new Player();
		player1.setFirstName("Josh");
		player1.setLastName("Juneau");
		player1.setGoals(5);
		Player player2 = new Player();
		player2.setFirstName("Duke");
		player2.setLastName("Java");
		player2.setGoals(15);
		Player player3 = new Player();
		player3.setFirstName("Jonathan");
		player3.setLastName("Gennick");
		player3.setGoals(1);
		Player player4 = new Player();
		player4.setFirstName("Bob");
		player4.setLastName("Smith");
		player4.setGoals(18);
		Player player5 = new Player();
		player5.setFirstName("Steve");
		player5.setLastName("Adams");
		player5.setGoals(7);

		team.add(player1);
		team.add(player2);
		team.add(player3);
		team.add(player4);
		team.add(player5);

	}

	public static void main(String[] args) {
		// Load team list
		loadTeam();

		// Sorting with Lambda Expressions
		// 1. Create a Comparator based upon the criteria by which we will
		// sort the List.
		Comparator<Player> byGoals = Comparator.comparing(Player::getGoals);
		System.out.println("== Sort by Number of Goals ==");
		// 2. Create a stream from the list, and invoke it's sorted() method,
		// passing the Comparator created in Step 1. Utilizing the builder
		// pattern, call the map method, using a lambda expression to map
		// each Player instance to a String representation. Finally, apply
		// the forEach method to the map, printing each String representation
		team.stream().sorted(byGoals).map(p -> p.getFirstName() + " " + p.getLastName() + " - " + p.getGoals())
				.forEach(element -> System.out.println(element));

		System.out.println("== Sort by Last Name ==");
		// Another way to sort without the use of streams. This time ordering
		// by last name
		Collections.sort(team, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
		team.stream().forEach((p) -> {
			System.out.println(p.getLastName());
		});

		arraySort1();
		arraySort2();
		arraySort3();
		
		createPlayer();
	}

	public static class PlayerUtility {
		public int compareByGoal(Player a, Player b) {
			int eval;
			if (a.getGoals() > b.getGoals()) {
				eval = 1;
			} else if (a.getGoals() < b.getGoals()) {
				eval = -1;
			} else {
				eval = 0;
			}
			return eval;
		}
	}
	public interface PlayerFactory {
		Player createPlayer(String position,
		int status,
		String firstName,
		String lastName);
		}
	

	private static void arraySort1() {

		Player[] teamArray = new Player[team.size()];
		teamArray = team.toArray(teamArray);

		Arrays.sort(teamArray, (p1, p2) -> Player.compareByGoal(p1, p2));

		System.out.println("== Array Sort by Goal #1 ==");
		for (Player player : teamArray) {
			System.out.println(player.getFirstName() + " " + player.getLastName() + " - " + player.getGoals());
		}
	}

	private static void arraySort2() {
		Player[] teamArray = team.toArray(new Player[team.size()]);
		PlayerUtility utility = new PlayerUtility();
		Arrays.sort(teamArray, utility::compareByGoal);

		System.out.println("== Array Sort by Goal #2 ==");
		for (Player player : teamArray) {
			System.out.println(player.getFirstName() + " " + player.getLastName() + " - " + player.getGoals());
		}
	}
	private static void arraySort3() {
		Integer[] ints = {3,5,7,8,51,33,1};
		Arrays.sort(ints, Integer::compare);
		
		System.out.println("== Integer Array Sort by value  ==");
		for (Integer integer : ints) {
			System.out.println(integer.toString());
		}
	}

	private static void createPlayer() {
		PlayerFactory player1 = Player::new;
		Player newPlayer = player1.createPlayer("CENTER", 0, "Constructor", "Referenceson");
		
		System.out.println(newPlayer);
	}

	
}
