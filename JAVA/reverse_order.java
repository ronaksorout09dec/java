

public class reverse_order {
    public static void main(String[] args) {
        Thread originalOrderThread = new Thread(new OriginalOrderPrinter());
        Thread reverseOrderThread = new Thread(new ReverseOrderPrinter());

        originalOrderThread.start();
        reverseOrderThread.start();
    }
}

class OriginalOrderPrinter implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("Original Order: " + i);
            try {
                Thread.sleep(100); // Adding a delay for illustration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ReverseOrderPrinter implements Runnable {
    @Override
    public void run() {
        for (int i = 50; i >= 1; i--) {
            System.out.println("Reverse Order: " + i);
            try {
                Thread.sleep(100); // Adding a delay for illustration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}