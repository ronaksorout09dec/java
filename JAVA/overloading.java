
        
class Print {
        public void display() {
            System.out.println("Display method without arguments");
        }
    
        public void display(int num) {
            System.out.println("Display method with integer argument: " + num);
        }
    
        public void display(String text) {
            System.out.println("Display method with string argument: " + text);
        }
    
public class overloading {

        public static void main(String[] args) {
            Print printer = new Print();
            printer.display();
            printer.display(42);
            printer.display("Hello, Overloading!");
        }
    }
    
}
