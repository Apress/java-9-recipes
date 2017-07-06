
package org.java9recipes.chapter05.recipe5_03;

import java.util.ArrayList;
import java.util.List;

/**
 * Recipe 5-3 Solution #2
 * 
 * Creating a Class That Can Only Have One Instance
 * 
 * @author juneau
 * @author anatolij kosorukov
 */
public enum StatisticsSingleton {
    INSTANCE;
    
    private List<Object> teams = new ArrayList<Object>();
    
    /**
     * @return the teams
     */
    public List<Object> getTeams() {
        return teams;
    }

    /**
     * @param teams the teams to set
     */
    public void setTeams(List<Object> teams) {
        this.teams = teams;
    }
    
}
