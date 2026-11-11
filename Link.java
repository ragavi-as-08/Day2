
class Node {

    int data;
    Node next;

    // Constructor
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {

    Node head = null;

    // Add node at last
    void addAtLast(int data) {

        Node ptr = new Node(data);

        if (head == null) {
            head = ptr;
        }
        else {

            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = ptr;
        }
    }

    void addAtBeginning(int data){
        Node ptr=new Node(data);
        if(head==null)
        {
        head=ptr;
        }
        else
        {
        ptr.next=head;
        head=ptr;
        }
    }

    void addAtposition(int data , int pos){
        Node ptr = new Node(data);
        int i = 1;
        Node temp = head;
        while(temp.next != null && i<pos){
            if(i == pos-1){
                ptr.next = temp.next;
                temp.next = ptr;
            }
            temp = temp.next;
            i++;
        }
    }

    
        void deleteFromEnd() {

            if (head == null) {
                System.out.println("List is empty");
                return;
            }

            if (head.next == null) {
                head = null;
                return;
            }

            Node temp = head;

            while (temp.next.next != null) {
                temp = temp.next;
            }

            temp.next = null;
        }

    
    void deleteAtPosition(int position) {

        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        if (position < 1) {
            System.out.println("Invalid position");
            return;
        }

        if (position == 1) {
            head = head.next;
            return;
        }

        Node temp = head;

        for (int i = 1; i < position - 1; i++) {

            if (temp.next == null) {
                System.out.println("Position out of range");
                return;
            }

            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Position out of range");
            return;
        }

        temp.next = temp.next.next;
    }

    void deleteFrombeginning(){
        if (head == null) {
                System.out.println("List is empty");
                return;
            }

        if (head.next == null) {
                head = null;
                return;
            }

        Node temp = head;
        head = temp.next;
        temp.next = null ;
    }

    // Display linked list
    void show() {

        Node temp = head;

        while (temp != null) {

            System.out.print(temp.data + " -> ");

            temp = temp.next;
        }

        System.out.println("null");
    }
}

public class Link {

    public static void main(String[] args) {

        LinkedList list = new LinkedList();

        list.addAtLast(10);
        list.addAtLast(20);
        list.addAtLast(30);
        list.addAtLast(40);
        list.addAtBeginning(50);
        list.addAtposition(60,3);
        list.deleteAtPosition(3);
        list.deleteFrombeginning();
        
        list.show();
    }
}
