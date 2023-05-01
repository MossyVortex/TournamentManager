import java.util.ArrayList;

public class AVLTree<T extends Comparable<T>> {

    private class Node {
        T data;
        Node left;
        Node right;
        int height;

        Node(T data) {
            this.data = data;
            this.height = 1;
        }
    }

    private Node root;

    // Insert a new element into the AVL tree
    public void insert(T data) {
        root = insert(root, data);
    }

    private Node insert(Node node, T data) {
        if (node == null) {
            return new Node(data);
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data);
        } else {
            return node; // Element already exists
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        if (balance > 1 && data.compareTo(node.left.data) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && data.compareTo(node.right.data) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Delete an element from the AVL tree
    public void delete(T data) {
        root = delete(root, data);
    }

    private Node delete(Node node, T data) {
        if (node == null) {
            return node;
        }

        if (data.compareTo(node.data) < 0) {
            node.left = delete(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                Node temp = null;
                if (temp == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node temp = minValueNode(node.right);
                node.data = temp.data;
                node.right = delete(node.right, temp.data);
            }
        }

        if (node == null) {
            return node;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Get the minimum value node of the AVL tree
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Get the height of a node
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Get the balance factor of a node
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // Perform a left rotation on a node
    private Node leftRotate(Node node) {
        Node temp1 = node.right;
        Node temp2 = temp1.left;

        temp1.left = node;
        node.right = temp2;

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        temp1.height = 1 + Math.max(getHeight(temp1.left), getHeight(temp1.right));

        return temp1;
    }

    // Perform a right rotation on a node
    private Node rightRotate(Node node) {
        Node temp1 = node.left;
        Node temp2 = temp1.right;

        temp1.right = node;
        node.left = temp2;

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        temp1.height = 1 + Math.max(getHeight(temp1.left), getHeight(temp1.right));

        return temp1;
    }

    // Traverse the AVL tree in-order
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    public ArrayList<T> getElements() {
        ArrayList<T> elements = new ArrayList<T>();
        getElementsHelper(root, elements);
        return elements;
    }
    
    private void getElementsHelper(Node node, ArrayList<T> elements) {
        if (node != null) {
            getElementsHelper(node.left, elements);
            elements.add(node.data);
            getElementsHelper(node.right, elements);
        }
    }

    // Test the AVL tree implementation
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        // Insert some elements into the AVL tree
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        // Traverse the AVL tree in-order
        System.out.print("In-order traversal: ");
        tree.inOrderTraversal();
        System.out.println();

        // Delete some elements from the AVL tree
        tree.delete(25);
        tree.delete(40);

        // Traverse the AVL tree in-order
        System.out.print("In-order traversal: ");
        tree.inOrderTraversal();
        System.out.println();
    }
}