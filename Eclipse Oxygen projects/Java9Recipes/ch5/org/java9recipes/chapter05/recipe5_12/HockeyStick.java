
package org.java9recipes.chapter05.recipe5_12;

/**
 * 
 * Recipe 5-12
 * 
 * Extending the Functionality of a Class
 * 
 * @author juneau
 */
public class HockeyStick {
 
    private int length;
    private boolean curved;
    private String material;
    
    public HockeyStick(int length, boolean curved, String material){
        this.length = length;
        this.curved = curved;
        this.material = material;
    }
    
     /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the curved
     */
    public boolean isCurved() {
        return curved;
    }

    /**
     * @param curved the curved to set
     */
    public void setCurved(boolean curved) {
        this.curved = curved;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

}
