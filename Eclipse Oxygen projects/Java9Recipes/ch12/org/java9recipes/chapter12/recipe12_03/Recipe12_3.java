package org.java9recipes.chapter12.recipe12_03;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.util.ResourceBundle;

/**
 *
 * @author John O'Conner (john@joconner.com)
 * Update: Juneau
 * Recipe 12-3:  Setting a Default Locale
 * @author anatolij kosorukov
 */
public class Recipe12_3 {
    
    private static final Date NOW = new Date();
    
    public void run() {
        // Set ALL locales to fr-FR
        Locale.setDefault(Locale.FRANCE);
        demoDefaultLocaleSettings();
        
        // System default is still fr-FR
        // DISPLAY default is es-MX
        // FORMAT default is en-US
        Locale.setDefault(Locale.Category.DISPLAY, Locale.forLanguageTag("es-MX"));
        Locale.setDefault(Locale.Category.FORMAT, Locale.US);
        demoDefaultLocaleSettings();
        
        // System default is still fr-FR
        // DISPLAY default is en-US
        // FORMAT default is es-MX
        Locale.setDefault(Locale.Category.DISPLAY, Locale.US);
        Locale.setDefault(Locale.Category.FORMAT, Locale.forLanguageTag("es-MX"));
        demoDefaultLocaleSettings();
        
        // System default is Locale.US
        // Resets both DISPLAY and FORMAT locales to en-US as well.
        Locale.setDefault(Locale.US);
        demoDefaultLocaleSettings();
        
    }
        
    public void demoDefaultLocaleSettings() {
        DateFormat df = 
            DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        ResourceBundle resource = 
            ResourceBundle.getBundle("org.java9recipes.chapter12.recipe12_03.SimpleResources",
                Locale.getDefault(Locale.Category.DISPLAY));
        String greeting = resource.getString("GOOD_MORNING");
        String date = df.format(NOW);
        System.out.printf("DEFAULT LOCALE: %s\n", Locale.getDefault());
        System.out.printf("DISPLAY LOCALE: %s\n", Locale.getDefault(Locale.Category.DISPLAY));
        System.out.printf("FORMAT LOCALE:  %s\n", Locale.getDefault(Locale.Category.FORMAT));
        System.out.printf("%s, %s\n\n", greeting, date );
    }
        
    public static void main(String[] args) {
        Recipe12_3 app = new Recipe12_3();
        app.run();
    }
}
