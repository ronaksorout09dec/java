

public class Missing_Elem {
    public static void main(String[] args) {
        int arr[] = {1,2,4,7,6,5};
        int n = arr.length;

        int Sum_Natural_Num = ((n+1)*(n+2))/2;
        System.out.println("The sum of Natural Number is :"+" "+Sum_Natural_Num);

        int sum=0;

        for (int i = 0; i < n; i++) {
            sum+=arr[i];
        }
        System.out.println("The sum of numbers in array is :"+" "+sum);

        int Missing_Num = Sum_Natural_Num - sum ;

        System.out.println("The Missing element in the array is :"+" "+Missing_Num);
    }
}