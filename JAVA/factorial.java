
import java.util.*;

public class factorial {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the Number for which you want factorial : ");
        int n = scan.nextInt();
        scan.close();

        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        System.out.println("Factorial of the number is : "+ factorial);
    }
}