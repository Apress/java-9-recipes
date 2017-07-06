
package org.java9recipes.chapter05.recipe5_08;

/**
 * Recipe 5-8
 * 
 * Constructing Instances of the Same Class with Different Values
 * 
 * @author juneau
 */
public class Roster {

    public static void main(String[] args) {

        TeamType myTeam = createTeam("Juneau Royals", "Chicago");
        System.out.println(myTeam.getFullName());
    }

    public static Team createTeam(String teamName, String city) {
        TeamBuilder builder = new HockeyTeamBuilder();
        builder.buildNewTeam(teamName);
        builder.designateTeamCity(city);
        builder.buildPlayerList();
        return builder.getTeam();
    }
}
