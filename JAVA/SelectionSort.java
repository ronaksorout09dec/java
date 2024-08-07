// import java.lang.reflect.Array;

public class SelectionSort {

    public static void selectionSort(int arr[]){
        for (int i = 0; i < arr.length; i++) {
            // Taking minimum element every iteration
            int Min_idx = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[Min_idx]) {
                    Min_idx = j;
                }
            }
            if(Min_idx != i){
                int temp = arr[Min_idx];
                arr[Min_idx] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {20,45,16,50,75,90};
        selectionSort(arr);
        System.out.println("Sorted array is :- ");
        // System.out.println(Array.toString(arr));
    }
}
