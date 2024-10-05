import java.util.Scanner;

public class countways {
    public static int countNum(int n){
        if (n<=1) {
            return n;
        }
        else {
            return countNum(n-1) + countNum(n-2);
}
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of stairs: ");
        int n = sc.nextInt();
        sc.close();
        int result = countNum(n+1);
        System.out.println("No of ways is : " + result);
    }
}
