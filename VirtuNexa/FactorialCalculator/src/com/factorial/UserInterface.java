package com.factorial;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the Factorial Calculator!");
        System.out.print("Enter a non-negative integer: ");
        
        int number = scanner.nextInt();
        
        if (number < 0) {
            System.out.println("Please enter a non-negative integer.");
            return;
        }

        long iterativeResult = Factorial.iterativeFactorial(number);
        long recursiveResult = Factorial.recursiveFactorial(number);

        System.out.println("Iterative Result: " + iterativeResult);
        System.out.println("Recursive Result: " + recursiveResult);
    }
}
