

public class AvgArr {
    public static void main(String[] args) {

        int arr[] = {2,4,6,8,10,12};

        int sum = 0;

        for (int i : arr) {
            sum += i;
            i++;
        }
        float avg = sum /(float) arr.length ;
        System.out.println(avg);
    }
}
