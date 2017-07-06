/*
 * Recipe 17-7
 */
package org.java9recipes.chapter20.recipe20_10;

import javax.json.*;

/**
 *
 * @author Juneau
 */
public class JsonBuilderExample {

    /**
     * Builds and returns a JsonObject representing a book
     * @return 
     */
 /**   public JsonObject buildBookObject() {
        JsonObject obj = new JsonBuilder()
                .beginObject()
                .add("title", "Java EE 7 Recipes")
                .beginObject("author")
                .add("firstName", "Josh")
                .add("lastName", "Juneau")
                .endObject()
                .beginObject("projectCoordinator")
                .add("firstName", "Kevin")
                .add("lastName", "Shea")
                .endObject()
                .beginArray("editor")
                .beginObject()
                .add("firstName", "Jonathan")
                .add("lastName", "Gennick")
                .endObject()
                .beginObject()
                .add("firstName", "James")
                .add("lastName", "Markham")
                .endObject()
                .endArray()
                .beginArray("technicalReviewer")
                .beginObject()
                .add("firstName", "Mark")
                .add("lastName", "Beaty")
                .endObject()
                .beginObject()
                .add("firstName", "David")
                .add("lastName", "Coffin")
                .endObject()
                .endArray()
                .endObject()
                .build();
        
        return obj;

    }
    
    public static void main(String[] args){
        JsonBuilderExample ex = new JsonBuilderExample();
        JsonObject obj = ex.buildBookObject();
     //   System.out.println("Object Sections: " + obj.getNames());
    }
    * */
}
