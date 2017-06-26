
package org.java9recipes.chapter03.recipe3_02;

/**
 * Recipe 3-2
 * 
 * Comparing Strings
 * 
 * @author juneau
 * @author anatolij
 */
public class StringExamples {

	public static void main(String[] args) {

		compareStrings();

	}

	public static void compareStrings() {
		String one = "one";
		String two = "two";

		String var1 = "one";
		String var2 = "Two";

		String pieceone = "o";
		String piecetwo = "ne";

		if (one.equals(var1)) {
			System.out.println("String one equals var1 using equals");
		} else {
			System.err.println("String one doesn't equal var1 using equals");
		}

		if (one.equals(two)) {
			System.out.println("String one equals two using equals");
		} else {
			System.err.println("String one doesn't equal two using equals");
		}

		if (two.equals(var2)) {
			System.out.println("String two equals var2 using equals");
		} else {
			System.err.println("String two doesn't equal var2 using equals");
		}

		if (one == var1) {
			System.out.println("String one equals var1 using ==");
		} else {
			System.err.println("String one doesn't equal var1 using ==");
		}

		if (two.equalsIgnoreCase(var2)) {
			System.out.println("String two equals var2 using equalsIgnoreCase");
		} else {
			System.err.println("String two doesn't equal var2 using equalsIgnoreCase");
		}

		System.out.println("\n\tTrying to use == on Strings that are pieced together\n");

		String piecedTogether = pieceone + piecetwo;

		if (one.equals(piecedTogether)) {
			System.out.println("The strings contain the same value using equals");
		} else {
			System.err.println("The strings don't contain the same value using equals");
		}

		if (one == piecedTogether) {
			System.out.println("The string contain the same value using == ");
		} else {
			System.err.println("The string doesn't contain the same value using == ");
		}

		if (one.compareTo(var1) == 0) {
			System.out.println("One is equal to var1 using compareTo()");
		} else {
			System.err.println("One isn't equal to var1 using compareTo()");
		}

		System.out.println("\n\tComparision using regionMatches\n");

		String sentence = "Java 8 is great!";
		String matchStr = "great";
		for (int i = 0; i <= sentence.length() - 1; i++) {
			if (sentence.regionMatches(i, matchStr, 0, matchStr.length())) {
				System.out.format("The sentence: '%s' matches: '%s' beginning at index: %d", sentence, matchStr, i);
				// System.out.println("The sentence: '" + sentence + "' matches: '" +
				// matchStr + "' beginning at index: " + i);
			}
		}

	}

}
