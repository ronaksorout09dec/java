
import java.util.*;

public class linearSearch {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array :- ");
        int n = scan.nextInt();
        System.out.print("Enter the array :- ");
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        System.out.print("Enter the number which you want to find in the array :- ");
        int x = scan.nextInt();
       // Implementation of linear search
       int idx = -1;
       for(int i=0; i<n; i++){
           if(arr[i] == x){
               idx = i;
               break;
           }
       }

       if(idx == -1){
           System.out.println("Searched element is not found in an array");
       }
       else{
           System.out.println("Searched element is found at the location:" +idx);
       }
        scan.close();
    }
}
