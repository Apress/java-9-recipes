package org.firstModule;
import org.secondModule.Calculator;
import java.math.BigDecimal;
public class Main {
    public static void main(String[] args) {
        System.out.println("This is my first module.");
        System.out.println("The hotel stay will cost " + Calculator.calculateRate(
             BigDecimal.TEN, new BigDecimal(22.95)
        ));
    }
}
