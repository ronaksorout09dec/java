import java.util.*;
public class multiplePrints {
    public static void printMultiples(int n , int K){
        if (K == 1) {
            System.out.println(n);
            return;
        }
        printMultiples(n,K-1);
        System.out.println(n*K);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number : ");
        int n = sc.nextInt();
        System.out.print("Enter the number of times you want the multiples : ");
        int k = sc.nextInt();
        sc.close();
        
        printMultiples(n,k);
        System.out.println();
    }
}
