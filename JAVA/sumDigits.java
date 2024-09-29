import java.util.Scanner;

public class sumDigits {
    public static int sumOfDigits(int num){
        // int result = 0;
        if (num == 0) {
            return 0;
        } else {
            return (num % 10) + sumOfDigits(num / 10);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number for which you want sum : ");
        int num = sc.nextInt();
        sc.close();
        int result = sumOfDigits(num);
        System.out.print("The final sum of the number is :" + result);
    }
}