
package org.java9recipes.chapter03.recipe3_06;

/**
 * Recipe 3-6
 * 
 * Converting Strings to Numeric Values
 * 
 * @author juneau
 * @author anatolij
 */
public class StringExamples {

	public static void main(String[] args) {

		stringsToNumbers();
		stringsToNumbersParseInt();

	}

	/**
	 * Solution #1 using Integer.valueOf() 
	 * 
	 * <div> For more information on the different {@code valueOf()} methods 
	 * that the {@link String} class or any other type class contains, 
	 * see the online Java documentation at
	 * <a href="http://download.java.net/java/jdk9/docs/">http://download.java.net/java/jdk9/docs/</a>
	 * </div>
	 */
	public static void stringsToNumbers() {
		String one = "1";
		String two = "2";

		int result = Integer.valueOf(one) + Integer.valueOf(two);

		System.out.println(result);

	}

	/**
	 * Solution #2 using Integer.parseInt()
	 */
	public static void stringsToNumbersParseInt() {
		String one = "1";
		String two = "2";

		int result = Integer.parseInt(one) + Integer.parseInt(two);

		System.out.println(result);
	}

}
