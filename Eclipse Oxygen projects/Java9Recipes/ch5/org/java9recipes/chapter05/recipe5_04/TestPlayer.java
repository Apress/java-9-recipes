
package org.java9recipes.chapter05.recipe5_04;

/**
 * Recipe 5-4
 * 
 * Generating Instances of a Class
 * 
 * @author Juneau
 */
public class TestPlayer {
    public static String GOALIE = "GOALIE";
    public static String CENTER = "CENTER";
    public static String DEFENSE = "DEFENSE";
    public static String LEFTWING = "LEFT";
    public static String RIGHTWING = "RIGHT";
    
    public static int ACTIVE = 0;
    public static int INACTIVE = 1;
    public static int INJURY = 2;
    
    public static void main(String[] args){
        
        Player player1 = PlayerFactory.createPlayer(GOALIE);
        player1.setStatus(ACTIVE);
        player1.setFirstName("Josh");
        player1.setLastName("Juneau");
        
        System.out.println(player1.playerString());
        
    }
    
}
