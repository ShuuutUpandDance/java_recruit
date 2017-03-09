package binarytree;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Zhang on 2017/3/7.
 */
public class Main {
    public static BinarySearchTree<Integer> createBST () {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(5);
        tree.insert(4);
        tree.insert(6);
        return tree;
    }

    public static void main(String[] args) {
        Stack<Node<Integer>>s = new Stack<>();
        BinarySearchTree<Integer> tree = createBST();


        System.out.println("Iterator: ");
        Iterator<Node<Integer>> iterator = tree.iterator();
        Iterator<Node<Integer>> ii = tree.iterator();
        while (ii.hasNext()) {
            Node<Integer> r = ii.next();
            if (r.data == 5) tree.remove(r);
        }
        while (iterator.hasNext()){
            System.out.print(iterator.next().data);
        }

    }
}
