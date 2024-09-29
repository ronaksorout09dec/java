import java.util.Scanner;

public class powerOfElement {
    public static int PowerFind(int a , int b){
        int mid = 0, result = 0, finalresult = 0;
        if(b == 1)
            return a;
        else
            mid = b/2;
            result = PowerFind(a,mid);
            finalresult = result*result;
            if(b % 2 == 0){
                return finalresult;
            }
            else{
                return finalresult*a;
            }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of a : ");
        int a = sc.nextInt();
        System.out.print("Enter the value of b : ");
        int b = sc.nextInt();
        sc.close();
        int result = PowerFind(a,b);
        System.out.print("The power of the a^b is : " + result);
    }
}
