

class Rectangle {
            private double length;
            private double breadth;
            
            public Rectangle(double length, double breadth) {
                this.length = length;
                this.breadth = breadth;
            }
        
            public double calculateArea() {
                return length * breadth;
            }

public class AreaRec {
            public static void main(String[] args) {
                Rectangle rectangle = new Rectangle(5.0, 3.0);
                double area = rectangle.calculateArea();
                System.out.println("Area of the rectangle: " + area);
        }
    }
}