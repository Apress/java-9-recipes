
package org.java9recipes.chapter05.recipe5_09;

import java.util.List;
import org.java9recipes.chapter05.recipe5_04.Player;

/**
 * Recipe 5-9
 * 
 * Interacting with a Class via Interfaces
 * 
 * @author juneau
 */
public interface TeamType {
    
    void setPlayers(List<Player> players);
    void setName(String name);
    void setCity(String city);
    String getFullName();

}
