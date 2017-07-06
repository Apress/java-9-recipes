

package org.java9recipes.chapter05.recipe5_06;

import java.util.ArrayList;

/**
 * Recipe 5-6
 * 
 * Defining an Interface for a Class
 * 
 * @author Juneau
 * @author anatolij kosorukov
 */
public class TestTeam {
    
    public static void main(String[] args){
        TeamType team1 = new Team();
        team1.setCity("Illinois");
        team1.setName("Josh");
        team1.setPlayers(new ArrayList<Player>());
        
        // Since an object of TeamType has been created, we are only able
        // to call upon the methods that were made available via the
        // TeamType interface
        System.out.println(team1.getFullName());
        
    }
}
