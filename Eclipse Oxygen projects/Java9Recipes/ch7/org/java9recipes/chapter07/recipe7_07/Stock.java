package org.java9recipes.chapter07.recipe7_07;

/**
 * Recipe 7-7:  Making Your Objects Iterable
 */

public class Stock {
    private String symbol;
    private String name;
    private double shares;
    public Stock(String symbol, String name, double shares) {
        this.symbol = symbol;
        this.name = name;
        this.shares = shares;
    }
    public String getSymbol() {
        return symbol;
    }
    public String getName() {
        return name;
    }
    public double getShares() {
        return shares;
    }
    public String toString() {
        return shares + " shares of " + symbol + " (" + name + ")";
    }
}