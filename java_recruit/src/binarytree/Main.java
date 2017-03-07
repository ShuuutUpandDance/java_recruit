package binarytree;

import stack_work.Stack;

/**
 * Created by Zhang on 2017/3/7.
 */
public class Main {
    public static Node createBST () {
        Node root = new Node(3);
        root.left = new Node(1);
        root.right = new Node(5);
        root.left.right = new Node(2);
        root.right.left = new Node(4);
        return root;
    }

    public static void main(String[] args) {
        Stack<Node>s = new Stack<>(10);
        BinarySearchTree tree = new BinarySearchTree(createBST());

        tree.preOrder(tree.root);
        System.out.print("\n");
        tree.midOrder(tree.root);
        System.out.print("\n");
        tree.postOrder(tree.root);
        System.out.print("\n");
        tree.preOrderWithoutRecur(tree.root);
        System.out.print("\n");
        tree.midOrderWithoutRecur(tree.root);
        System.out.print("\n");
        tree.LayerTraverse(tree.root);
    }
}
