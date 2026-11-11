import java.util.Scanner;
class Node {
int data;
Node left, right;
Node(int value) {
    data = value;
    left = null;
    right = null;
}
}
class BST {
Node root = null;
// Insert Node
void insert(int value) {
    Node newNode = new Node(value);
    if (root == null) {
        root = newNode;
        return;
    }
    Node temp = root;
    while (true) {
        if (value < temp.data) {
            if (temp.left == null) {
                temp.left = newNode;
                break;
            }
            temp = temp.left;
        }
        else {
            if (temp.right == null) {
                temp.right = newNode;
                break;
            }
            temp = temp.right;
        }
    }
}
// Inorder Traversal
void inorder(Node temp) {
    if (temp == null)
        return;
    inorder(temp.left);
    System.out.print(temp.data + " ");
    inorder(temp.right);
}
// Preorder Traversal
void preorder(Node temp) {
    if (temp == null)
        return;
    System.out.print(temp.data + " ");
    preorder(temp.left);
    preorder(temp.right);
}
// Postorder Traversal
void postorder(Node temp) {
    if (temp == null)
        return;
    postorder(temp.left);
    postorder(temp.right);
    System.out.print(temp.data + " ");
}
}
public class BinarySearchTree {
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    BST tree = new BST();
    int choice, value;
    do {

        System.out.println("\n1. Insert");
        System.out.println("2. Inorder");
        System.out.println("3. Preorder");
        System.out.println("4. Postorder");
        System.out.println("5. Exit");

        System.out.print("Enter Choice: ");
        choice = sc.nextInt();

        switch (choice) {

            case 1:
                System.out.print("Enter Value: ");
                value = sc.nextInt();
                tree.insert(value);
                break;

            case 2:
                System.out.println("Inorder:");
                tree.inorder(tree.root);
                break;

            case 3:
                System.out.println("Preorder:");
                tree.preorder(tree.root);
                break;

            case 4:
                System.out.println("Postorder:");
                tree.postorder(tree.root);
                break;

            case 5:
                System.out.println("Exit");
                break;

            default:
                System.out.println("Invalid Choice");
        }

    } while (choice != 5);

    sc.close();
 }
}
