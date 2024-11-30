

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
    public void InsertionAtEnd(int newData) {
        Node newNode = new Node(newData);

        // If the list is empty, make the new node the head
        if (head == null) {
            head = newNode;
            return;
        }

        // Traverse to the last node
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        // Add the new node at the end
        temp.next = newNode;
    }

    // Implementation of insertion of a node in the middle 
    public void InsertionInBeginning(int newData) {
        Node newNode = new Node(newData);
        newNode.next = head;
        head = newNode;
    }

    // Implementation of insertion of a node after any particular node
    public void InsertionAfter(Node prev_Node , int newData){
        if(prev_Node == null){
            System.out.println("The previous node should not contain null value");
            return;
        }

        Node newNode = new Node(newData);
        newNode.next = prev_Node.next;
        prev_Node.next = newNode;

    }

// Implimentation of reversal of a node in a linkedlist

    public void ReverseLL(){
        Node curr = head;
        Node prev = null;
        Node nextPtr = null;
        while (curr!=null) {
            nextPtr = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextPtr;
        }
        head = prev;
        return;
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
        list.InsertionAtEnd(2);
        list.InsertionAtEnd(4);
        list.InsertionAtEnd(8);
        System.out.println("Before insertion of 10: ");
        list.displayLL();
        System.out.println("After insertion of 10: ");
        list.InsertionAtEnd(10);
        list.displayLL();



        list.InsertionInBeginning(10);
        list.InsertionInBeginning(20);
        list.InsertionInBeginning(30);
        list.InsertionInBeginning(40);
        System.out.println("Before insertion of 5: ");
        list.displayLL();
        System.out.println("After insertion of 5: ");
        list.InsertionInBeginning(5);
        list.displayLL();
        
        System.out.println("After insertion at a particular node");
        list.InsertionAfter(list.head.next.next, 13);
        list.displayLL();

        list.ReverseLL();
        System.out.println("Reversal in a linkedlist :- ");
        list.displayLL();
    }
}