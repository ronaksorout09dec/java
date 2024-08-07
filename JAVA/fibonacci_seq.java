

public class fibonacci_seq {
        public static void main(String[] args) {
            int n = 10;
            int a = 0, b = 1;
    
            System.out.print("Fibonacci Sequence: ");
            for (int i = 0; i < n; i++) {
                System.out.print(a + " ");
                int sum = a + b;
                a = b;
                b = sum;
            }
        }
    }