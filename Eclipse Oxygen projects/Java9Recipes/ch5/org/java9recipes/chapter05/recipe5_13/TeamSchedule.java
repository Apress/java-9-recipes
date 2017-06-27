package org.java9recipes.chapter05.recipe5_13;

import java.time.LocalDate;
import java.util.Map;

/**
 * Recipe 5-13
 *
 * Defining a Template for Classes to Extend
 *
 * @author juneau
 * @author anatolij kosorukov
 */
public class TeamSchedule extends Schedule {

    public TeamSchedule(String teamName) {
        super(teamName);
    }

    @Override
    void calculateDaysPlayed(int month) {
        int totalGamesPlayedInMonth = 0;
        for (Map.Entry<Team, LocalDate> entry : gameMap.entrySet()) {
            if (entry.getKey().getName().equals(teamName)
                    && Integer.valueOf(entry.getValue().getMonth().getValue()).equals(month)) {
                totalGamesPlayedInMonth++;
            }
        }
        System.out.println("Games played in specified month: " + totalGamesPlayedInMonth);
    }

}
