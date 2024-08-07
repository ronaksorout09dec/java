import java.util.Scanner;  
class BinaryToHexa   
{  
int number;      
void getNumber()   
{  
try (Scanner sc = new Scanner(System.in)) {
    System.out.print("Enter the number: ");  
    //reads number from the user to convert  
    number = Integer.parseInt(sc.nextLine(), 2);
} catch (NumberFormatException e) {
    e.printStackTrace();
}  
}  
//function to convert the number from binary to hexadecimal  
void convert()   
{  
String hexa = Integer.toHexString(number);  
//prints hexadecimal value  
System.out.println("Hexadecimal Value is: " + hexa);  
}  
}  
//main class  
public class number_system  
{  
//main method      
public static void main(String args[])   
{  
BinaryToHexa btoh = new BinaryToHexa();  
btoh.getNumber();  
btoh.convert();  
}  
}  