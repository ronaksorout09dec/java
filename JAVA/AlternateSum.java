import java.util.*;

public class AlternateSum {
    public static int alternateSumFind(int num) {
        if (num == 0) {
            return 0;
        }
        else{
            if (num % 2 == 0) {
                return alternateSumFind(num - 1) - num;
            }
            else{
                return alternateSumFind(num - 1) + num;
            }
        }
    }
        
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number for which you want Aternate sum : ");
        int num = sc.nextInt();
        sc.close();

        int result = alternateSumFind(num);
        System.out.println("The final result of the alternate number is : " + result);
    }
}
