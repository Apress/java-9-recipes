package org.java9recipes.chapter04.recipe4_07;

import java.time.Clock;
import java.time.LocalDate;

/**
 * Recipe 4-7
 *
 * Obtaining the Current Date
 *
 * @author juneau
 */
public class DateExamples {

    public static void main(String[] args) {
        newDate();
        newDateFromClock();
    
    }

    /**
     * Obtains the current date
     */
    public static void newDate() {
        LocalDate date = LocalDate.now();
        System.out.println("Current Date: " + date);
    }

    public static void newDateFromClock() {
        Clock clock = Clock.systemUTC();
        LocalDate date = LocalDate.now(clock);
        System.out.println("Date from clock: " + date);
    }

   
    
}
