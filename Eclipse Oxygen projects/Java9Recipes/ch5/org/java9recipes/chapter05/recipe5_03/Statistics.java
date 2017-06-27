
package org.java9recipes.chapter05.recipe5_03;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Recipe 5-3 Solution #1
 * 
 * Creating a Class That Can Only Have One Instance
 * 
 * @author juneau
 * @author anatolij kosorukov
 */
public class Statistics implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -353939186927641662L;

	// Definition for the class instance
    private static volatile Statistics instance = new Statistics();

    private List<?> teams = new ArrayList<Object>();

    /**
     * Constructor has been made private so that outside classes do not have 
     * access to instantiate more instances of Statistics.
     */
    private Statistics(){ 
    }

    /**
     * Accessor for the statistics class.  Only allows for one instance of the
     * class to be created.
     * @return 
     */
    public static Statistics getInstance(){

        return instance;
    }

    /**
     * @return the teams
     */
    public List<?> getTeams() {
        return teams;
    }

    /**
     * @param teams the teams to set
     */
    public void setTeams(List<?> teams) {
        this.teams = teams;
    }
    
    protected Object readResolve(){
        return instance;
    }
    
}
