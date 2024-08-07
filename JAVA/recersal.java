

public class recersal {
    public static void main(String[] args) {
        int array[] = {2,4,6,8,10};
        int n = array.length;
        for (int i = 0; i < n/2 ; i++) {
            int temp = array[i];
            array[i] = array[n-i-1];
            array[n-i-1] = temp;
        }

        System.out.print("Reversal of an array is:"+" ");
        for (int i = 0; i < n ; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}