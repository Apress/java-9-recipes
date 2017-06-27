
package org.java9recipes.chapter05.recipe5_08;


/**
 * Recipe 5-8
 * 
 * @author juneau
 */
public class Goalie extends Player implements PlayerType {
    
    private int totalSaves;
    
    public Goalie(){
        this.setPosition("GOALIE");
    }
    
    /**
     * @return the totalSaves
     */
    public int getTotalSaves() {
        return totalSaves;
    }

    /**
     * @param totalSaves the totalSaves to set
     */
    public void setTotalSaves(int totalSaves) {
        this.totalSaves = totalSaves;
    }
    
    
}
