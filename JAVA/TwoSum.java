import java.util.HashMap;
import java.util.Scanner;

public class TwoSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // size of an array
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();
        
        // array elements
        System.out.print("Enter the array elements: ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        // target value
        System.out.print("Enter the target value: ");
        int target = sc.nextInt();
        
        // create the hashmap
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }
        
        int[] result = {-1, -1}; // Initialize result array with -1
        
        // main logic of two sum problem
        for (int i = 0; i < n; i++) {
            if (map.containsKey(target - arr[i])) {
                // the element is not repeatable
                if (map.get(target - arr[i]) > i) {
                    result[0] = i;
                    result[1] = map.get(target - arr[i]);
                    break;
                }
            }
        }
        
        // display the final result
        System.out.print("The resultant array index is: [" + result[0] + "," + result[1] + "]");
        
        sc.close();
    }
}