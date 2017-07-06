
package org.java9recipes.chapter05.recipe5_13;


/**
 * Recipe 5-13
 * Defining a Template for Classes to Extend
 * @author juneau
 */
public interface TeamType {
    
    void setName(String name);
    void setCity(String city);
    String getFullName();

}
