package binarytree;


import stack_work.Stack;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Zhang on 2017/3/7.
 */
public class BinarySearchTree<T extends Comparable<T>> {
    public Node root;

    BinarySearchTree(Node root) {
        this.root = root;
    }

    public boolean contains(T t) {
        if (root == null) return false;

        Node cur = root;
        while (true) {
            if (t.compareTo((T) cur.data) < 0) {
                if (cur.left != null) {
                    cur = cur.left;
                } else {
                    return false;
                }
            } else if (t.compareTo((T) cur.data) > 0) {
                if (cur.right != null) {
                    cur = cur.right;
                } else {
                    return false;
                }
            } else return true;
        }
    }

    public boolean insert(T i) {
        if (root == null) {
            root = new Node(i);
            return true;
        }

        Node current = root;
        while (true) {
            // 如果 i 比当前结点的值小
            if (i.compareTo((T) current.data) < 0) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new Node(i);
                    break;
                }
            } else {
                if (current.right != null)
                    current = current.right;
                else {
                    current.right = new Node(i);
                    break;
                }
            }
        }
        return true;
    }

    public  void preOrder(Node root) {
        Node cur = root;
        if (cur == null) System.out.println("An empty tree!");
        else {
            System.out.print(cur.data);
            if (cur.left != null) preOrder(cur.left);
            if (cur.right != null) preOrder(cur.right);
        }
    }

    public  void midOrder(Node root) {
        Node cur = root;
        if (cur == null) System.out.println("An empty tree!");
        else {
            if (cur.left != null) midOrder(cur.left);
            System.out.print(cur.data);
            if (cur.right != null) midOrder(cur.right);
        }
    }

    public  void postOrder(Node root) {
        Node cur = root;
        if (cur == null) System.out.println("An empty tree!");
        else {
            if (cur.left != null) postOrder(cur.left);
            if (cur.right != null) postOrder(cur.right);
            System.out.print(cur.data);
        }
    }

    public void midOrderWithoutRecur(Node root) {
        if (root == null)
            return;

        Stack<Node> s = new Stack<>(64);
        Node current;

        s.push(root);

        while (! s.isEmpty()) {
            current = s.getTop();
            if (current.state == 0) {
                if (current.left != null)
                    s.push(current.left);
                current.state = 1;
            }
            else if (current.state == 1) {
                System.out.print(current.data);
                current.state = 2;
            }
            else if (current.state == 2) {
                if (current.right != null)
                    s.push(current.right);
                current.state = 3;
            }
            else if (current.state == 3) {
                s.pop();
                current.state = 0;
            }
        }
    }

    public void preOrderWithoutRecur(Node root) {
        if (root == null)
            return;

        Stack<Node> s = new Stack<>(64);
        Node current;

        s.push(root);

        while (! s.isEmpty()) {
            current = s.getTop();
            if (current.state == 0) {
                System.out.print(current.data);
                current.state = 1;
            }
            else if (current.state == 1) {
                if (current.left != null)
                    s.push(current.left);
                current.state = 2;
            }
            else if (current.state == 2) {
                if (current.right != null)
                    s.push(current.right);
                current.state = 3;
            }
            else if (current.state == 3) {
                s.pop();
                current.state = 0;
            }
        }
    }

    public void LayerTraverse(Node root) {
        if (root == null) return;

        Queue<Node>q = new ArrayDeque<>();

        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            System.out.print(cur.data);
            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }
}
