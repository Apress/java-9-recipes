
package org.java9recipes.chapter05.recipe5_08;

/**
 * Recipe 5-8
 * @author juneau
 */
public interface TeamBuilder {
    public void buildPlayerList();
    public void buildNewTeam(String teamName);
    public void designateTeamCity(String city);
    public Team getTeam();

}
