
package org.java9recipes.chapter05.recipe5_07;

import java.util.List;

/**
 * Recipe 5-7
 *
 * @author Juneau
 */
public interface TeamType {

    List<Player> getPlayers();

    void setPlayers(List<Player> players);

    void setName(String name);

    void setCity(String city);

    String getFullName();

    default void listPlayers() {
        getPlayers().stream().forEach((player) -> {
            System.out.println(player.getFirstName() + " " + player.getLastName());
        });
    }
    
}
