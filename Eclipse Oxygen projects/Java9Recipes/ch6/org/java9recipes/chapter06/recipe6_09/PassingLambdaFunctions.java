

package org.java9recipes.chapter06.recipe6_09;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Recipe 6-9
 * @author Juneau
 */
public class PassingLambdaFunctions {
    /**
     * Calculates a value based upon the calculation function that is passed
     * in.
     * @param f1
     * @param args
     * @param x
     * @param y
     * @param z
     * @return 
     */
    public Double calculate(Function<List<Double>, Double> f1, 
                                  Double [] args){
        Double returnVal;
        List<Double> varList = new ArrayList<>();
        int idx = 0;
        while (idx < args.length){
            varList.add(args[idx]);
            idx++;
        }
        returnVal=f1.apply(varList);
        
        return returnVal;
    }
}
