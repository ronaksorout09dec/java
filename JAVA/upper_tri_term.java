

import java.util.Scanner;

class upper_tri_term{
        public static void main(String args[])
        {
           Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of rows");
        int m = sc.nextInt();

        System.out.println("Enter number of columns");
        int n = sc.nextInt();
        
        int arr[][] = new int[m][n];

        System.out.println("Enter the matrix elements");
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        sc.close();

        for (int i = 0; i < m-1 ; i++) {
          for (int j = 0; j < n-1; j++) {
            if(i + j < m - 1)System.out.print(arr[i][j] + "  ");
          }
        }
      }
    }