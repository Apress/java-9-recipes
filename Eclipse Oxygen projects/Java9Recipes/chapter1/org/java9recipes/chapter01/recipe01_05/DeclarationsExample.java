package org.java9recipes.chapter01.recipe01_05;

public class DeclarationsExample {
    public static void main (String[] args) {
        boolean BooleanVal = true;  /* Default is false */
        
        char charval = 'G';     /* Unicode UTF-16 */
        charval = '\u0490';     /* Ukrainian letter Ghe(Ґ) */

        byte byteval_min = Byte.MIN_VALUE;       /*  8 bits, -128 to 127 */
        byte byteval_max = Byte.MAX_VALUE;       /*  8 bits, -128 to 127 */
        
        short shortval_min = Short.MIN_VALUE;     /* 16 bits, -32,768 to 32,767 */
        short shortval_max = Short.MAX_VALUE;     /* 16 bits, -32,768 to 32,767 */
        
        int intval_min = Integer.MIN_VALUE;         /* 32 bits, -2147483648 to 2147483647 */
        int intval_max = Integer.MAX_VALUE;         /* 32 bits, -2147483648 to 2147483647 */
        
        long longval_min = Long.MIN_VALUE;       /* 64 bits, -(2^64) to 2^64 - 1 -> -9223372036854775808 to 9223372036854775807*/
        long longval_max = Long.MAX_VALUE;       /* 64 bits, -(2^64) to 2^64 - 1 -> -9223372036854775808 to 9223372036854775807*/

        float   floatval = 10.123456F; /* 32-bit IEEE 754 */
        double doubleval = 10.12345678987654; /* 64-bit IEEE 754 */

        String message = "Darken the corner where you are!"; 
        message = message.replace("Darken", "Brighten");
        
        System.out.format("BooleanVal = %b\n",BooleanVal);
        System.out.format("charval = %c\n",charval);
        System.out.format("byteval_min = %d\n",byteval_min);
        System.out.format("byteval_max = %d\n",byteval_max);
        System.out.format("shortval_min = %d\n",shortval_min);
        System.out.format("shortval_max = %d\n",shortval_max);
        System.out.format("intval_min = %d\n",intval_min);
        System.out.format("intval_max = %d\n",intval_max);
        System.out.format("longval_min = %d\n",longval_min);
        System.out.format("longval_max = %d\n",longval_max);
        System.out.format("floatval = %f\n",floatval);
        System.out.format("doubleval = %e\n",doubleval);
        System.out.println(message);
        
    }
}
     
