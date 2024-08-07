

import java.util.ArrayList;
import java.util.List;

public class missingElem {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 7, 6, 9,11};
        // int n = arr.length;

        int max = arr[0];
        int min = arr[0];
        
        // Find the minimum and maximum values in the array
        for (int num : arr) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        List<Integer> missingNumbers = new ArrayList<>();

        // Create a boolean array to mark visited numbers
        boolean[] visited = new boolean[max - min + 1];

        // Mark visited numbers in the array
        for (int num : arr) {
            visited[num - min] = true;
        }

        // Find and store missing numbers
        for (int i = min; i <= max; i++) {
            if (!visited[i - min]) {
                missingNumbers.add(i);
            }
        }

        System.out.println("Missing Numbers: " + missingNumbers);
    }
}