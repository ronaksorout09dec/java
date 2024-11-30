

public class LinkedList {
    Node head;

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    // Implementation of insertion of a node at the end
    // public void InsertionAtEnd(int newData) {
    //     Node newNode = new Node(newData);

    //     // If the list is empty, make the new node the head
    //     if (head == null) {
    //         head = newNode;
    //         return;
    //     }

    //     // Traverse to the last node
    //     Node temp = head;
    //     while (temp.next != null) {
    //         temp = temp.next;
    //     }

    //     // Add the new node at the end
    //     temp.next = newNode;
    // }

    // Implementation of insertion of a node in the middle 
    public void InsertionInBeginning(int newData) {
        Node newNode = new Node(newData);
        newNode.next = head;
        head = newNode;
    }
    // Implementation of displaying the linked list
    public void displayLL() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        // list.InsertionAtEnd(2);
        // list.InsertionAtEnd(4);
        // list.InsertionAtEnd(8);

        list.InsertionInBeginning(10);
        list.InsertionInBeginning(20);
        list.InsertionInBeginning(30);
        list.InsertionInBeginning(40);
        

        // System.out.println("Before insertion of 10: ");
        System.out.println("Before insertion of 5: ");

        list.displayLL();

        // list.InsertionAtEnd(10);
        list.InsertionInBeginning(5);

        // System.out.println("After insertion of 10: ");
        System.out.println("After insertion of 5: ");

        list.displayLL();
    }
}