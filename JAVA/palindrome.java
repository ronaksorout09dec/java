

public class palindrome {
    public static void main(String[] args) {
        int Arr[] = {1,2,3,2,1};
        int n = Arr.length;
        int flag = 0;

        for (int i = 0; i < n/2 ; i++) {

            if (Arr[i] != Arr[n-i-1]) {
                flag = 1;
                System.out.println("It is not a palindromic array");
            }
        }

        if (flag==0) {
            System.out.println("It is an palindromic Array");
        }
    }   
}