

package org.java8recipes.chapter18.recipe18_08;

import java.math.BigDecimal;

/**
 * 
 * @author Juneau
 */
public interface PositionType {
    
    public double hourlyWage(BigDecimal hours, BigDecimal wage);
    
    /**
     * Hourly salary calculation
     * @param wage
     * @return 
     */
    default public BigDecimal yearlySalary(BigDecimal wage){
        return (wage.multiply(new BigDecimal(40))).multiply(new BigDecimal(52));
    }
}
