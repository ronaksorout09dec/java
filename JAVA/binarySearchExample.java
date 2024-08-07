

public class binarySearchExample {
    public static int BinarySearch(int arr[],int target){
        int low = 0;
        int high = arr.length;
        while (low<=high) {
            int mid = (low+high)/2;
            if (arr[mid]==target) {
                return mid;
            }
            else if (arr[mid]<target){
                low = mid + 1;
            }
            else if (arr[mid]>target){
                high = mid - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int arr[] = {10,20,30,40,50,60};
        int target = 50;
        int index = BinarySearch(arr, target);
        if (index != -1) {
            System.out.println("The index value of the target is : "+" "+index);
        } else {
            System.out.println("The target is not present in the array");
        }
    }
}
