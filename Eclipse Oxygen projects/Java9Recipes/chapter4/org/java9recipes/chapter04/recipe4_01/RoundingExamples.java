
package org.java9recipes.chapter04.recipe4_01;

/**
 * Recipe 4-1
 * 
 * Rounding Float and Double Values to Integers
 * @author juneau
 * @author anatolij
 */
public class RoundingExamples {
    
    public static void main(String[] args){
        // Round a float value to an Integer
    	//Contractors of Float and Double classes are deprecated. 
        System.out.println(roundFloatToInt(Float.valueOf("8.837")));
        System.out.println(roundDoubleToLong(Double.valueOf("9.9")));
        
        float nanFloat = Float.NaN;
        nanFloat = 0f/0f;
        
        float positiveInfinity_float = Float.POSITIVE_INFINITY;
        float negativeInfinity_float = Float.NEGATIVE_INFINITY;
        positiveInfinity_float = 1f/0f;
        negativeInfinity_float = -1f/0f;
        System.out.println("\t\n Float class");
        System.out.format("If the argument is 'Not a Number (%f)',"
        		+ " then a zero will be returned (%d)\n", Float.NaN, roundFloatToInt(nanFloat));
        System.out.format("If the argument is '1/0 (%f)',"
        		+ " then Float.POSITIVE_INFINITY will be returned (%d)\n", Float.POSITIVE_INFINITY, roundFloatToInt(positiveInfinity_float));
        System.out.format("If the argument is '-1/0 (%f)',"
        		+ " then Float.NEGATIVE_INFINITY will be returned (%d)\n", Float.NEGATIVE_INFINITY, roundFloatToInt(negativeInfinity_float));
    
    
        double nanDouble = Double.NaN;
        nanDouble = 0d/0d;
        
        double positiveInfinity_double = Double.POSITIVE_INFINITY;
        double negativeInfinity_double = Double.NEGATIVE_INFINITY;
        positiveInfinity_double = 1f/0f;
        negativeInfinity_double = -1f/0f;
        System.out.println("\t\n Double class");
        System.out.format("If the argument is 'Not a Number (%g)',"
        		+ " then a zero will be returned (%d)\n", Double.NaN, roundDoubleToLong(nanDouble));
        System.out.format("If the argument is '1/0 (%g)',"
        		+ " then Float.POSITIVE_INFINITY will be returned (%d)\n", Double.POSITIVE_INFINITY, roundDoubleToLong(positiveInfinity_double));
        System.out.format("If the argument is '-1/0 (%g)',"
        		+ " then Float.NEGATIVE_INFINITY will be returned (%d)\n", Double.NEGATIVE_INFINITY, roundDoubleToLong(negativeInfinity_double));
    
        
    
    }
    
    /**
     * Rounds a floating-point number to an Integer and returns the result
     * @param myFloat
     * @return 
     */
    public static int roundFloatToInt(float myFloat){
        return Math.round(myFloat);
    }

    /**
     * Rounds a Double value to an Integer and returns the result
     * @param myDouble
     * @return 
     */
    public static long roundDoubleToLong(double myDouble){
        return Math.round(myDouble);
    }
    
}
