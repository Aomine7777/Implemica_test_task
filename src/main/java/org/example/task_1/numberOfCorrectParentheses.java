package org.example.task_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class numberOfCorrectParentheses {
    public static void main(String[] args) {
        // Create a Scanner object for input from the keyboard
        Scanner scanner = new Scanner(System.in);

        // Input N from the keyboard
        System.out.print("Enter the number N: ");
        int N = scanner.nextInt();

        // Check that N is non-negative
        if (N < 0) {
            System.out.println("N should be a non-negative number.");
            return; // End the program if N is negative
        }

        // Generate valid parentheses expressions for the given N
        List<String> expressions = generateParentheses(N);

        // Output the result
        System.out.println("Example:");
        System.out.println("N = " + N + " answer " + expressions.size());

        // Output all generated parentheses expressions
        for (String expr : expressions) {
            System.out.println(expr);
        }

        // Output the total number of valid parentheses expressions
        System.out.println("So there are only " + expressions.size() + " option(s) where all open parentheses are correctly opened/closed.");
    }

    /**
     * Method to generate all valid parentheses expressions.
     *
     * @param n The number of pairs of parentheses to generate
     * @return A list of valid parentheses expressions
     */
    private static List<String> generateParentheses(int n) {
        List<String> result = new ArrayList<>();
        // Call the recursive method to generate the expressions
        generateRecursive(result, "", 0, 0, n);
        return result; // Return all found valid expressions
    }

    /**
     * Recursive method to generate valid parentheses expressions.
     *
     * @param result The list to which valid expressions are added
     * @param current The current expression being built
     * @param open The number of open parentheses in the current expression
     * @param close The number of close parentheses in the current expression
     * @param max The maximum number of pairs of parentheses
     */
    private static void generateRecursive(List<String> result, String current, int open, int close, int max) {
        // If the length of the current expression equals 2 * max (all parentheses are added), add it to the result
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        // If the number of open parentheses is less than the maximum, add an open parenthesis
        if (open < max) {
            generateRecursive(result, current + "(", open + 1, close, max);
        }

        // If the number of close parentheses is less than the number of open parentheses, add a close parenthesis
        if (close < open) {
            generateRecursive(result, current + ")", open, close + 1, max);
        }
    }
}