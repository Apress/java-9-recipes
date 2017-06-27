
package org.java9recipes.chapter05.recipe5_13;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 
 * Recipe 5-13
 * 
 * Defining a Template for Classes to Extend
 * 
 * @author juneau
 */
public abstract class Schedule {
    
    public String scheduleYear;
    public String teamName;
    
    public List<Team> teams;
    
    public Map<Team, LocalDate> gameMap;
    
    public Schedule(){}
    
    public Schedule(String teamName){
        this.teamName = teamName;
    }
    
    abstract void calculateDaysPlayed(int month);
    
}
