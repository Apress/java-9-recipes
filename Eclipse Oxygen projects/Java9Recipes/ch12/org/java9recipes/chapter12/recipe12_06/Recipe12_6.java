
package org.java9recipes.chapter12.recipe12_06;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 *
 * @author John O'Conner (john@joconner.com)
 * Update:  Juneau
 * Recipe 12-6: Overriding the Default Currency
 */
public class Recipe12_6 {

    public Recipe12_6() {
    }
    
    public void run() {        
        BigDecimal value = new BigDecimal(12345);
 
        Locale.setDefault(Locale.JAPAN);
        System.out.printf("Default locale: %s\n", Locale.getDefault().getDisplayName());
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String formattedCurrency = nf.format(value);
        System.out.printf("%s\n", formattedCurrency);
        nf.setCurrency(Currency.getInstance(Locale.US));
        formattedCurrency = nf.format(value);
        System.out.printf("%s\n\n", formattedCurrency);

        Locale.setDefault(Locale.US);
        System.out.printf("Default locale: %s\n", Locale.getDefault().getDisplayName());
        nf = NumberFormat.getCurrencyInstance();
        formattedCurrency = nf.format(value);
        System.out.printf("%s\n", formattedCurrency);
        nf.setCurrency(Currency.getInstance("JPY"));
        formattedCurrency = nf.format(value);
        System.out.printf("%s\n\n", formattedCurrency);
        
        
        Locale.setDefault(Locale.FRANCE);
        System.out.printf("Default locale: %s\n", Locale.getDefault().getDisplayName());
        nf = NumberFormat.getCurrencyInstance();
        formattedCurrency = nf.format(value);
        System.out.printf("%s\n", formattedCurrency);
        nf.setCurrency(Currency.getInstance("USD"));
        formattedCurrency = nf.format(value);
        System.out.printf("%s\n\n", formattedCurrency);
        
        
    }
    
    public static void main(String[] args) {
        Recipe12_6 app  = new Recipe12_6();
        app.run();
    }
}
