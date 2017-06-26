package org.java9recipes.chapter04.recipe4_05;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
//import javax.money.Monetary;
//import javax.money.MonetaryAmount;
//import javax.money.format.AmountFormatQuery;
//import javax.money.format.MonetaryAmountFormat;
//import javax.money.format.MonetaryFormats;
//import org.javamoney.moneta.FastMoney;
//import org.javamoney.moneta.Money;


/**
 * Recipe 4-5
 * 
 * Calculating Monetary Values
 * 
 * @author juneau
 */
public class MonetaryCalculation {
   
    public static void main(String[] args) {
        // Run the currency calculation examples
        calculateDollars();
        
        javaMoneyCalculation();
    }

    /**
     * Formats a double value into currency format and then returns it as
     * a String
     * @param value
     * @return 
     */
    public static String formatDollars(double value) {
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return dollarFormat.format(value);
    }

    public static void calculateDollars() {
        BigDecimal currencyOne = new BigDecimal("25.65");
        BigDecimal currencyTwo = new BigDecimal("187.32");
        BigDecimal currencyThree = new BigDecimal("4.86");
        BigDecimal result = null;
        String printFormat = null;

        // Add all three values
        result = currencyOne.add(currencyTwo).add(currencyThree);
        // Convert to double and send to formatDollars(), returning a String
        printFormat = formatDollars(result.doubleValue());
        System.out.println(printFormat);

        // Subtract the first currency value from the second
        result = currencyTwo.subtract(currencyOne);
        printFormat = formatDollars(result.doubleValue());
        System.out.println(printFormat);

    }
    
    /**
     * FIXME JSR 354: Money and Currency API is not added to JDK (175)
     */
    public static void javaMoneyCalculation(){
//        System.out.println("------Java Money API-----");
//
//        MonetaryAmount amount1 =  Money.of(25.65, Monetary.getCurrency("USD"));
//        MonetaryAmount amount2 =  Money.of(187.32, Monetary.getCurrency("USD"));
//        MonetaryAmount amount3 =  Money.of(4.86,Monetary.getCurrency("USD"));
//
//        MonetaryAmount result = null;
//        result = amount1.add(amount2).add(amount3);
//
//        MonetaryAmountFormat printFormat = MonetaryFormats.getAmountFormat(
//            AmountFormatQuery.of(Locale.US));
//        System.out.println("Sum of all: " + printFormat.format(result));
//
//        result = amount2.subtract(amount1);
//        System.out.println("Subtract amount1 from amount 2: " + printFormat.format(result));
    }
}
