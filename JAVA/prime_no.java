

public class prime_no {
    public static void main(String[] args) {
        int sum = 0;
            for (int i = 2 ; i <= 100 ; i++) {
                if (i%1==0 && i%i==0) {
                    sum += i;
                System.out.print(sum + " ");                       
            }
    }
}
}