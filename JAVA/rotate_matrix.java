
import java.util.*;


public class rotate_matrix {

    public static void ReverseMatrix(int arr[][]){
        int m = arr.length;
        int n = arr[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = i; j < n; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }

        for (int i = 0; i < m; i++) {
            int leftIndex = 0;
            int rightIndex = n-1;

            while (leftIndex<rightIndex) {
                int temp = arr[i][leftIndex];
                arr[i][leftIndex] = arr[i][rightIndex];
                arr[i][rightIndex] = temp;

                leftIndex++;
                rightIndex--;
            }
        }

  }
    public static void main(String[] args) {
        int arr[][] = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}
        };

        ReverseMatrix(arr);

        for (var mat : arr) {
            System.out.println(Arrays.toString(mat));
        }
    }
}
