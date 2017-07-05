package org.java9recipes.chapter21.recipe21_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Recipe 23-5
 * 
 * Generating and Reading from URLs
 * 
 * @author juneau
 */
public class GenerateAndReadUrl {

    public static void main(String[] args) {
        try {
            // Generate absolute URL
            URL url1 = new URL("http://www.java.net");
            System.out.println(url1.toString());

            // Generate URL for pages with a common base
            URL url2 = new URL(url1, "search/node/jdk7");

            // Generate URL from different pieces of data
            URL url3 = new URL("http", "java.net", "search/node/jdk7");

            readFromUrl(url1);
            readFromUrl(url2);
            readFromUrl(url3);
            
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Open URL stream as an input stream and print contents to command line.
     * 
     * @param url 
     */
    public static void readFromUrl(URL url) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    url.openStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }

            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
