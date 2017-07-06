

package org.java9recipes.chapter05.recipe5_07;

import java.util.ArrayList;
import java.util.List;

/**
 * Recipe 5-7
 * 
 * Modifying Interfaces without Breaking Existing Code
 * 
 * @author Juneau
 * @author anatolij kosorukov
 */
public class InterfaceTester {
    public static void main(String[] args){
        Player player1 = new HockeyPlayer();
        player1.setFirstName("Jonathan" );
        player1.setLastName("Gennick");
        Player player2 = new HockeyPlayer();
        player2.setFirstName("Josh");
        player2.setLastName("Juneau");
        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        
        Team team = new Team();
        team.setPlayers(playerList);
        // Call the default method
        team.listPlayers();
        
    }
}
