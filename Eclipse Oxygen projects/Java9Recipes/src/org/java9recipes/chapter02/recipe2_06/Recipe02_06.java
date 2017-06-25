package org.java9recipes.chapter02.recipe2_06;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static java.net.http.HttpResponse.asFile;
import static java.net.http.HttpResponse.asString;
import static java.net.http.HttpResponse.ignoreBody;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.util.UUID.fromString;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Recipe 2-6: HTTP Client
 *
 * @author Juneau
 */
public class Recipe02_06 {

    public static void main(String[] args) {

        HttpResponse r1;
        try {
            r1 = HttpRequest.create(new URI("http://www.apress.com/us/"))
                    .GET()
                    .response();

            int responseCode = r1.statusCode();
            if(responseCode == 200){
                System.out.println(r1.body(asString()));
            }

         } catch (URISyntaxException|IOException|InterruptedException ex) {
            Logger.getLogger(Recipe02_06.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
