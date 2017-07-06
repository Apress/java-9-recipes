

package org.java9recipes.chapter06.recipe6_08;

/**
 * Recipe 6-8:  Accessing Class Variables from a Lambda Expression
 * @author Juneau
 */
public class MainClass {
    public static void main(String[] args){
        System.out.println("==VariableAccess==");
        VariableAccess va = new VariableAccess();
        va.lambdaInMethod("Hello");
        
//        System.out.println("==VariableAccessInner==");
//        VariableAccessInner vai = new VariableAccessInner();
//        VariableAccessInner.InnerClass inner = vai.new InnerClass();
//        inner.lambdaInMethod("Hello");
        
    }
}
