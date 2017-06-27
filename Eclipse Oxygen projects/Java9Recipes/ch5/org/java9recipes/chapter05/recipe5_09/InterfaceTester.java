
package org.java9recipes.chapter05.recipe5_09;


/**
 * Recipe 5-9
 * 
 * Interacting with a Class via Interfaces
 * 
 * @author juneau
 */
public class InterfaceTester {
    
    static TeamType team = new Team();
    
    public static void main(String[] args){
        team.setCity("SomeCity");
        team.setName("SomeName");
        team.setPlayers(null);
        System.out.println(team.getFullName());
    }
    
}
