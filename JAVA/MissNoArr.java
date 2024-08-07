

public class MissNoArr {
    public static void main(String[] args) {
        int n = 7;
        int arr[] = {2,4,7,6,5,1};
        int sum = 0;
        int AccSum = (n*(n+1))/2;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        System.out.println("Sum of given Array is :- "+sum);
        System.out.println("Acctual sum of the Array is :- "+ AccSum);
        int MissingNo = AccSum-sum;
        System.out.println("Missing term in an array is :- "+MissingNo);
    }
}