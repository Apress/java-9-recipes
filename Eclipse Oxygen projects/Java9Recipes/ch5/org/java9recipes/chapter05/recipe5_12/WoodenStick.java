
package org.java9recipes.chapter05.recipe5_12;

/**
 * Recipe 5-12
 * 
 * Extending the Functionality of a Class
 * 
 * @author juneau
 */
public class WoodenStick extends HockeyStick {
    
    private static final String material = "WOOD";
    private int lie;
    private int flex;
    
    public WoodenStick(int length, boolean isCurved){
        super(length, isCurved, material);
    }
    
    public WoodenStick(int length, boolean isCurved, int lie, int flex){
        super(length, isCurved, material);
        this.lie = lie;
        this.flex = flex;
    }

    /**
     * @return the lie
     */
    public int getLie() {
        return lie;
    }

    /**
     * @param lie the lie to set
     */
    public void setLie(int lie) {
        this.lie = lie;
    }

    /**
     * @return the flex
     */
    public int getFlex() {
        return flex;
    }

    /**
     * @param flex the flex to set
     */
    public void setFlex(int flex) {
        this.flex = flex;
    }
}
