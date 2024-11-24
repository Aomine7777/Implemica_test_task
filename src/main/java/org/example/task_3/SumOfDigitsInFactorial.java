package org.example.task_3;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class SumOfDigitsInFactorial {
    public static void main(String[] args) {

        // Compute the sum of digits in the factorial of 100

        // Compute the factorial of 100 using IntStream to multiply numbers from 1 to 100
        int sumOfDigits = IntStream.rangeClosed(1, 100)  // Generates a stream of integers from 1 to 100
                .mapToObj(BigInteger::valueOf)          // Converts each integer to BigInteger
                .reduce(BigInteger.ONE, BigInteger::multiply)  // Multiplies all numbers to compute 100!
                .toString()  // Converts the BigInteger result to a string
                .chars()  // Converts the string to a stream of characters (each character is a digit)
                .map(Character::getNumericValue)  // Converts each character to its numeric value (digit)
                .sum();  // Sums all the digits

        // Output the result
        System.out.println("The sum of the digits in 100! is: " + sumOfDigits);  // Prints the result
    }
}
