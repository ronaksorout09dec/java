import java.util.Arrays;
import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements you want to store: ");
        int number = scanner.nextInt();

        int[] num = new int[number];
        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < number; i++) {
            num[i] = scanner.nextInt();
        }

        int i = 0, j = 0;
        while (j < number) {
            if (num[j] != 0) {
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
                i++;
            }
            j++;
        }

        System.out.println("Sorted Array: " + Arrays.toString(num));
        scanner.close();
    }
}