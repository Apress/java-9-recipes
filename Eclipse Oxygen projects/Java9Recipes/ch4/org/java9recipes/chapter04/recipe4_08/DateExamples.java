
package org.java9recipes.chapter04.recipe4_08;

import java.time.LocalDate;
import java.time.Month;

/**
 * Recipe 4-8
 * 
 * Obtaining a Date with Given Criteria
 * 
 * @author juneau
 */
public class DateExamples {

    public static void main(String[] args) {
       newSpecifiedDate();

    }

     public static void newSpecifiedDate() {
        LocalDate date = LocalDate.of(2000, Month.NOVEMBER, 11);
        System.out.println("Date from specified date: " + date);
    }

   
}
