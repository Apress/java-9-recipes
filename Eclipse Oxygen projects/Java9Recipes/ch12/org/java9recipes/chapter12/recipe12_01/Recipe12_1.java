
package org.java9recipes.chapter12.recipe12_01;

/**
 *
 * @author John O'Conner (john@joconner.com)
 * Updated:  Juneau
 * Recipe 12-1:  Converting Unicode Characters to Digits
 *
 */
public class Recipe12_1 {

    public static void main(String[] args) {
        Recipe12_1 example = new Recipe12_1();
        example.run();
    }
    
    
    public void run() {
        int x = 0;
        for (int c=0; c <= 0x10FFFF; c++) {
            if (Character.isDigit(c)) {
                ++x;
                System.out.printf("Codepoint: 0x%04X\tCharacter: %c\tDigit: %d\tName: %s\n", c, c, Character.digit(c, 10), Character.getName(c));              
            }        }
        System.out.printf("Total digits: %d\n", x);
    }    
}
