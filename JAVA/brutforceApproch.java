import java.util.*;

public class brutforceApproch {
        public static int findSumMatrix(int arr[][],int r1,int r2,int c1,int c2){
            int sum = 0;
            for (int i = r1; i <= r2; i++) {
                for (int j = c1; j <= c2; j++) {
                    sum += arr[i][j];
                }
            }
            return sum ;
    }
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.print("Enter the number of row :- ");
            int m = scan.nextInt();
            System.out.print("Enter the number of column :- ");
            int n = scan.nextInt();

            System.out.println("Enter the Matrix :- ");
            int arr[][] = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = scan.nextInt();
                }
            }
            int r1,r2,c1,c2;
                    System.out.print("Enter r1 :- ");
            r1=scan.nextInt();
                    System.out.print("Enter c1 :- ");
            c1=scan.nextInt();
                    System.out.print("Enter r2 :- ");
            r2=scan.nextInt();
                    System.out.print("Enter c2 :- ");
            c2=scan.nextInt();
                int result = findSumMatrix(arr, r1, r2, c1, c2);
                System.out.print("Sum of the elements of the given elements is :- "+result);
        }
    }
}
