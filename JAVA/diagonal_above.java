
import java.util.Scanner;

public class diagonal_above {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the size of the square matrix
        System.out.print("Enter the size of the square matrix: ");
        int size = scanner.nextInt();

        // Initialize the square matrix
        int[][] matrix = new int[size][size];

        // Input elements into the matrix
        System.out.println("Enter the elements of the square matrix:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Print elements above the secondary diagonal
        System.out.println("Elements above the secondary diagonal:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i + j < size - 1) {
                    System.out.print(matrix[i][j] + " ");
                }
            }
        }

        // Close the scanner
        scanner.close();
    }
}
