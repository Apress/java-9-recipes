
package org.java9recipes.chapter04.recipe4_03;

/**
 * Recipe 4-3
 * 
 * Comparing int Values
 * 
 * @author juneau
 */
public class ComparingValues {
    
    public static void main(String[] args){
        compareIntegers();
    }
    
    
    public static void compareIntegers(){
        int int1 = 1;
        int int2 = 10;
        int int3 = -5;
        int int1_tmp = int1;

        System.out.println(int1 == int2);

        System.out.println(int3 == int1);

        System.out.println(int1 == int1_tmp);

        System.out.println(int1 > int3);

        System.out.println(int2 < int3);
        
        System.out.println("Compare method -> int3 and int1: " + Integer.compare(int3, int1));

        System.out.println("Compare method -> int2 and int1: " + Integer.compare(int2, int1));
    }
    
}
