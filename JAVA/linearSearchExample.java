

import java.util.Scanner;

public class linearSearchExample{
public static int linearSearch(int arr[],int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i]==target) {
            return i;
        }
    }
    return -1;
}
    public static void main(String[] args) {
        int arr[] = {2,5,8,3,10};
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the target number :");
        int target = scan.nextInt();
        int index = linearSearch(arr, target);
        if (index != -1) {
            System.out.println("The index value of the target is : "+" "+index);
        } else {
            System.out.println("The target is not present in the array");
        }
        scan.close();
    }
    }