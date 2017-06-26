
package org.java9recipes.chapter04.recipe4_02;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Recipe 4-2
 * 
 * Formatting double and long Decimal Values
 * 
 * @author juneau
 * @author anatolij
 */
public class FormatDouble {

    public static void main(String[] args) {
    	//Contractors of Double class are deprecated.
        formatDouble(Double.valueOf("345.9372"));
    }

    public static void formatDouble(double myDouble) {
        NumberFormat numberFormatter = new DecimalFormat("##.000");
        String result = numberFormatter.format(myDouble);

        System.out.println(result);

        // Obtains an instance of NumberFormat class
        NumberFormat format = NumberFormat.getInstance();

// Format a double value for the current locale
        String result2 = format.format(83.404);
        System.out.println("Current Locale: " + result2);

// Format a double value for an Italian locale
        result = NumberFormat.getInstance(Locale.ITALIAN).format(83.404);
        System.out.println("Italian Locale: " + result);

// Parse a String into a Number
        try {
            Number num = format.parse("75");
            System.out.println("Now a number: " + num);
        } catch (java.text.ParseException ex) {
            System.out.println(ex);
        }

    }
}
