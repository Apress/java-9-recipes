package org.secondModule;
import java.math.BigDecimal;
public class Calculator {
    public static BigDecimal calculateRate(BigDecimal days, BigDecimal rate) {
        return days.multiply(rate);
    }
}
