
package org.java9recipes.chapter04.recipe4_09;

import java.time.Month;
import java.time.MonthDay;
import java.time.YearMonth;

/**
 * Recipe 4-9
 * 
 * Obtaining the Year-Month-Day Combination
 * 
 * @author juneau
 */
public class DateExamples {

    public static void main(String[] args) {
       obtainYearMonth();
       obtainMonthDay();
    }

    public static void obtainYearMonth() {
        YearMonth yearMo = YearMonth.now();
        System.out.println("Current Year and month:" + yearMo);
        
        YearMonth specifiedDate = YearMonth.of(2000, Month.NOVEMBER);
        System.out.println("Specified Year-Month: " + specifiedDate);
    }
    
    public static void obtainMonthDay(){
        MonthDay monthDay = MonthDay.now();
        System.out.println("Current month and day: " + monthDay);
        
        MonthDay specifiedDate = MonthDay.of(Month.NOVEMBER, 11);
        System.out.println("Specified Month-Day: " + specifiedDate);
    }
   
}
