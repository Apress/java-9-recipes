
package org.java9recipes.chapter05.recipe5_04;



/**
 * Recipe 5-4 Factory
 * 
 * @author juneau
 */
public class PlayerFactory {
    
    public static Player createPlayer(String playerType){
        Player returnType;
        switch(playerType){
        case "GOALIE":
            returnType = new Goalie();
            break;
        case "LEFT":
            returnType = new LeftWing();
            break;
        case "RIGHT":
            returnType = new RightWing();
            break;
        case "CENTER":
            returnType = new Center();
            break;
        case "DEFENSE":
            returnType = new Defense();
            break;
        default:
            returnType = new AllPosition();
        }
        return returnType;
    }
}
