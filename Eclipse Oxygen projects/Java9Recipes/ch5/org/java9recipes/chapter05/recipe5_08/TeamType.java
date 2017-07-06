
package org.java9recipes.chapter05.recipe5_08;

import java.util.List;

/**
 * Recipe 5-8
 * 
 * @author juneau
 */
public interface TeamType {
    
    void setPlayers(List<Player> players);
    void setName(String name);
    void setCity(String city);
    String getFullName();

}
