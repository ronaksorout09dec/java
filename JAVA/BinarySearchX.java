public class BinarySearchX {
    public static int binarySearch1( int[] arr, int low, int high, int x){
        while(low <= high){
            int mid = (low + high) / 2;
            if(arr[mid] == x)
                return mid;
            if(arr[mid] < x)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {2,4,8,12,16,19,21,27,29,35};
        int low = 0; 
        int high = arr.length-1;
        int x = 27;
        int result = binarySearch1(arr,low,high,x);
        System.out.println("The element is at position :" + result);
    }
}