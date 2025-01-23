import java.util.*;

public class LargestRectangleArea {
    static int largestRectangleArea(int[] h) {
        int n = h.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            // Treat current height as 0 for the sentinel
            int currentHeight = (i == n) ? 0 : h[i];
            // Process stack if current height is less than stack top
            while (!stack.isEmpty() && currentHeight < h[stack.peek()]) {
                int height = h[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter The No. Of Buildings :- ");
        int[] h = new int[sc.nextInt()];
        for (int i = 0; i < h.length; i++) {
            System.out.print("Height of building " + (i + 1) + ": ");
            h[i] = sc.nextInt();
        }
        
        System.out.println("Largest Rectangle Area in Histogram is :- " + largestRectangleArea(h));
        sc.close();
    }
}
