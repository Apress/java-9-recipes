/*
 * Recipe 17-4
 */
package org.java9recipes.chapter20.recipe20_11;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import org.java9recipes.chapter20.recipe20_10.JsonBuilderExample;

/**
 *
 * @author Juneau
 */
public class JsonWriterExample {

    public static void main(String[] args) {
       /* try {
            JsonBuilderExample builderEx = new JsonBuilderExample();
            JsonObject jsonObject = builderEx.buildBookObject();
            StringWriter writer = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(writer);
            jsonWriter.writeObject(jsonObject);
            jsonWriter.close();
            writer.close();
            // Write file
            FileWriter fstream = new FileWriter("BookObject.json");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(writer.toString());
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(JsonWriterExample.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }
}
