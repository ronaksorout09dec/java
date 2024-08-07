

import java.util.Scanner;

public class LargestElementIn2DArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of rows and columns for the 2D array
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();

        // Initialize the 2D array
        int[][] matrix = new int[rows][cols];

        // Input elements into the matrix
        System.out.println("Enter the elements of the 2D array:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Find the largest element in the 2D array
        int largestElement = matrix[0][0];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] > largestElement) {
                    largestElement = matrix[i][j];
                }
            }
        }

        // Print the largest element
        System.out.println("The largest element in the 2D array is: " + largestElement);

        // Close the scanner
        scanner.close();
    }
}