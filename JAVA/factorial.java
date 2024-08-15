import java.util.Scanner;

class factorialFind {
    public static int factorialFinding(int n) {
        int result = 1;
        if (n == 0 || n == 1) {
            return 1;
        } else {
            result = n * factorialFinding(n - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the Number for which you want factorial : ");
        int n = scan.nextInt();

        int result = factorialFinding(n);
        System.out.println("Factorial of " + n + " is : " + result);

        scan.close();
    }
}