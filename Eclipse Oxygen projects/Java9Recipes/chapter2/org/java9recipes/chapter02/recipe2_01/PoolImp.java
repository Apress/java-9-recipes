package org.java9recipes.chapter02.recipe2_01;

public class PoolImp implements Pool {

	public PoolImp() {
		
	}
	
	public static void main(String[] args) {
		
		Pool pool = new PoolImp();
		
		double length, width, depth;
		length = 1;
		width = 1;
		depth = 1;
		
		double squareOrRectConstantDepth = pool.squareOrRectConstantDepth(length, width, depth);
		System.out.format("Square or rect constant depth = %f\n",squareOrRectConstantDepth);
		
		double shallowDepth=1, middleDepth=1, deepDepth=1;
		double squareOrRectVariableDepth = pool.squareOrRectVariableDepth(length, width, shallowDepth, middleDepth, deepDepth);
		System.out.format("Square or rect variable depth = %f\n",squareOrRectVariableDepth);
	}
}
