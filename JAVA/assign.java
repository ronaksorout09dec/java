

import java.util.*;

public class assign {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the values of m and n
        System.out.print("Enter the number of row : ");
        int row = scanner.nextInt();
        System.out.print("Enter the number of column : ");
        int column = scanner.nextInt();

        // Initialize counters
        int positiveCount = 0;
        int negativeCount = 0;
        int oddCount = 0;
        int evenCount = 0;
        int zeroCount = 0;

        // Input m * n integer numbers
        System.out.println("Enter the Matrix :- ");
        int arr[][] = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int num = arr[i][j];
                num = scanner.nextInt();

                if (num > 0) {
                    positiveCount++;
                } else if (num < 0) {
                    negativeCount++;
                }

                if (num % 2 == 0) {
                    evenCount++;
                } else {
                    oddCount++;
                }

                if (num == 0) {
                    zeroCount++;
                }
            }
        }

        // Print the counts
        System.out.println("Number of positive numbers: " + positiveCount);
        System.out.println("Number of negative numbers: " + negativeCount);
        System.out.println("Number of odd numbers: " + oddCount);
        System.out.println("Number of even numbers: " + evenCount);
        System.out.println("Number of zeros: " + zeroCount);

        // Close the scanner
        scanner.close();
    }
}
