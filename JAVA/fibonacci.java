import java.util.Scanner;

public class fibonacci {
    public static int fibonacciSeries(int n){
        int result = 0;
        if (n<=1) {
            return n;
        } else {
            result = fibonacciSeries(n-1) + fibonacciSeries(n-2);
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number n of fibonacci : ");
        int n = sc.nextInt();
        int result = fibonacciSeries(n);
        System.out.println("The final Output is :" + result);
    }
}