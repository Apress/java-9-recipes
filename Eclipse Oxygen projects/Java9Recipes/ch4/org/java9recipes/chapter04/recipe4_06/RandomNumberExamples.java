
package org.java9recipes.chapter04.recipe4_06;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Recipe 4-6
 * 
 * Randomly Generating Values
 * 
 * @author juneau
 * @author anatolij kosorukov
 *
 */
public class RandomNumberExamples {

	public static void main(String[] args) {
		randomExamples();
		threadLocalRandomExamples();
	}

	public static void randomExamples() {
		System.out.println("Each time you run, the results will be different!");
		// Create a new instance of the Random class
		Random random = new Random();
		System.out.println("Random: " + random);

		// Generates a random Integer
		int myInt = random.nextInt();
		System.out.println("Random int: " + myInt);
		// Generates a random Double value
		double myDouble = random.nextDouble();
		System.out.println("Random double: " + myDouble);

		// Generates a random float
		float myFloat = random.nextFloat();
		System.out.println("Random float: " + myFloat);

		// Generates a random Gaussian double
		// mean 0.0 and standard deviation 1.0
		// from this random number generator's sequence.
		double gausDouble = random.nextGaussian();
		System.out.println("Random Gaussian double: " + gausDouble);
		// Generates a random Long
		long myLong = random.nextLong();
		System.out.println("Random long: " + myLong);
		// Generates a random boolean
		boolean myBoolean = random.nextBoolean();
		System.out.println("Random boolean: " + myBoolean);
		double rand = Math.random();
		System.out.println("Random double via Math: " + rand);

	}

	/**
	 * @see <a href=
	 *      "http://download.java.net/java/jdk9/docs/api/java/util/concurrent/ThreadLocalRandom.html">http://download.java.net/java/jdk9/docs/api/java/util/concurrent/ThreadLocalRandom.html</a>
	 */
	public static void threadLocalRandomExamples() {
		System.out.println("Each time you run, the results will be different!");
		// Create a new instance of the Random class

		Random random = ThreadLocalRandom.current();
		System.out.println("Random: " + random);

		// Generates a random Integer
		int myInt = random.nextInt();
		System.out.println("Random int: " + myInt);
		// Generates a random Double value
		double myDouble = random.nextDouble();
		System.out.println("Random double: " + myDouble);

		// Generates a random float
		float myFloat = random.nextFloat();
		System.out.println("Random float: " + myFloat);

		// Generates a random Gaussian double
		// mean 0.0 and standard deviation 1.0
		// from this random number generator's sequence.
		double gausDouble = random.nextGaussian();
		System.out.println("Random Gaussian double: " + gausDouble);
		// Generates a random Long
		long myLong = random.nextLong();
		System.out.println("Random long: " + myLong);
		// Generates a random boolean
		boolean myBoolean = random.nextBoolean();
		System.out.println("Random boolean: " + myBoolean);
		double rand = Math.random();
		System.out.println("Random double via Math: " + rand);

	}

	/**
	 * @see <a href=
	 *      "http://download.java.net/java/jdk9/docs/api/java/security/SecureRandom.html">http://download.java.net/java/jdk9/docs/api/java/security/SecureRandom.html</a>
	 */
	public static void secureRandomExamples() {
		System.out.println("Each time you run, the results will be different!");
		// Create a new instance of the Random class

		Random random = new SecureRandom();
		System.out.println("Random: " + random);

		// Generates a random Integer
		int myInt = random.nextInt();
		System.out.println("Random int: " + myInt);
		// Generates a random Double value
		double myDouble = random.nextDouble();
		System.out.println("Random double: " + myDouble);

		// Generates a random float
		float myFloat = random.nextFloat();
		System.out.println("Random float: " + myFloat);

		// Generates a random Gaussian double
		// mean 0.0 and standard deviation 1.0
		// from this random number generator's sequence.
		double gausDouble = random.nextGaussian();
		System.out.println("Random Gaussian double: " + gausDouble);
		// Generates a random Long
		long myLong = random.nextLong();
		System.out.println("Random long: " + myLong);
		// Generates a random boolean
		boolean myBoolean = random.nextBoolean();
		System.out.println("Random boolean: " + myBoolean);
		double rand = Math.random();
		System.out.println("Random double via Math: " + rand);

	}
}
