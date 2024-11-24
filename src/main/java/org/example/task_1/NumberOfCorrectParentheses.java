package org.example.task_1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class NumberOfCorrectParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter the number of pairs of parentheses (N): ");
        int N;
        try {
            N = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a non-negative integer.");
            return;
        }

        // Validate input
        if (N < 0) {
            System.out.println("N should be a non-negative integer.");
            return;
        }

        // Generate valid parentheses expressions
        List<String> validExpressions = generateParentheses(N);

        // Output example and result
        System.out.println("Example:");
        System.out.println("N = " + N + " -> Answer: " + validExpressions.size());
        System.out.println("Generated Parentheses Expressions:");
        for (String expression : validExpressions) {
            System.out.println(expression);
        }

        // Output total count
        System.out.println("\nThere are " + validExpressions.size() + " valid combinations where all open parentheses are correctly closed.");
    }

    /**
     * Generates all valid parentheses expressions for a given number of pairs.
     *
     * @param n The number of pairs of parentheses
     * @return A list of valid parentheses expressions
     */
    private static List<String> generateParentheses(int n) {
        List<String> results = new ArrayList<>();
        generateParenthesesRecursive(results, new StringBuilder(), 0, 0, n);
        return results;
    }

    /**
     * Recursive method to generate valid parentheses expressions.
     *
     * @param results The list to store valid expressions
     * @param current The current expression being built
     * @param openCount The number of open parentheses used
     * @param closeCount The number of close parentheses used
     * @param max The maximum number of pairs of parentheses
     */
    private static void generateParenthesesRecursive(List<String> results, StringBuilder current, int openCount, int closeCount, int max) {
        // If the expression length reaches the maximum (2 * max), add to results
        if (current.length() == max * 2) {
            results.add(current.toString());
            return;
        }

        // Add an open parenthesis if the count is less than max
        if (openCount < max) {
            current.append('(');
            generateParenthesesRecursive(results, current, openCount + 1, closeCount, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }

        // Add a close parenthesis if it does not exceed the open count
        if (closeCount < openCount) {
            current.append(')');
            generateParenthesesRecursive(results, current, openCount, closeCount + 1, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }
}