package org.java9recipes.chapter01.recipe01_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AcceptingInput {
	public static void main(String[] args) {
		BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in));
		String numberAsString = "";
		long numberAsLong = 0;
		boolean numberIsValid = false;
		do {
			/* Ask the user for a number. */
			System.out.print("Please enter a number: ");
			try {
				numberAsString = readIn.readLine();
				
				System.out.format("You entered %s", numberAsString);
			} catch (IOException ex) {
				System.out.println(ex);
			}
			/* Convert the number into binary form. */
			try {
				numberAsLong = Long.parseLong(numberAsString);
				numberIsValid = true;
			} catch (NumberFormatException nfe) {
				System.out.format("%d is not a number!\n", numberAsLong);
			}
		} while (numberIsValid == false);
	}
}