

public class MiddleRowAndColumn {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int size = 3; // Size of the square matrix
        
        displayMiddleRowAndColumn(matrix, size);
    }

    public static void displayMiddleRowAndColumn(int[][] matrix, int size) {
        if (size % 2 != 0) {
            // Check if the matrix has odd dimensions
            int middleRow = size / 2;
            int middleCol = size / 2;

            // Display the elements of the middle row
            System.out.println("Middle Row:");
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[middleRow][j] + " ");
            }
            System.out.println();

            // Display the elements of the middle column
            System.out.println("Middle Column:");
            for (int i = 0; i < size; i++) {
                System.out.println(matrix[i][middleCol]);
            }
        } else {
            System.out.println("The matrix does not have odd dimensions.");
        }
    }
}