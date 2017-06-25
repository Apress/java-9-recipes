package org.java9recipes.chapter01.recipe01_08;

public class StringConversion {
    
    public static void main (String[] args) {
        double pi;
        String strval;

        pi = Double.parseDouble("3.14");
        strval = String.valueOf(pi);
        System.out.println(strval);
        System.out.println(Double.toString(pi));
        System.out.println(Boolean.parseBoolean(String.valueOf(1)));;
    }
    
}
