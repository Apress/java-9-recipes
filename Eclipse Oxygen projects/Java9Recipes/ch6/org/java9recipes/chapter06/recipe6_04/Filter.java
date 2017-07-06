

package org.java9recipes.chapter06.recipe6_04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Recipe 6-4:  Specifying Filter Criteria on a Collection of Data
 * @author Juneau
 * @author anatolij kosorukov
 */
public class Filter {
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
    
    public static void main(String[] args){
        // Load team list
        loadTeam();
        
        // Create Array to store the matching results
        List<Player> gteTenGoals = new ArrayList<>();
        
        Comparator<Player> byGoals = Comparator.comparing(Player::getGoals);
        team.stream().sorted(byGoals)
        .map(p -> p.getFirstName() + " " + p.getLastName() + " - "
        + p.getGoals())
        .forEach(element -> System.out.println(element));
        
        System.out.println("==Find ==");
        
        // Create a stream from the team List, then call the filter
        // method on the stream and apply the desired filtering criteria
        // to each object in the stream.  For each matching object, add
        // to the gteTenGoals array.
        team.stream().filter(
                p -> p.getGoals() >= 10
                && p.getStatus() == -1)
                .forEach(element -> gteTenGoals.add(element));
        System.out.println("Number of Players Matching Criteria: " + gteTenGoals.size());
    }
}
