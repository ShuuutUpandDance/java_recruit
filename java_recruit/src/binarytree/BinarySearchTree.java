package binarytree;


import stack_work.Stack;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import token_adaptor.*;
import static token_adaptor.Token.TokenType.*;

/**
 * Created by Zhang on 2017/3/7.
 */
public class BinarySearchTree<T extends Comparable<T>> {
    public Node<T> root;

    public boolean contains(T t) {
        if (root == null) return false;

        Node<T> cur = root;
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
            root = new Node<T>(i);
            root.parent = null;
            return true;
        }

        Node<T> current = root;
        while (true) {
            // 如果 i 比当前结点的值小
            if (i.compareTo((T) current.data) < 0) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new Node<T>(i);
                    current.left.parent = current;
                    break;
                }
            } else {
                if (current.right != null)
                    current = current.right;
                else {
                    current.right = new Node<T>(i);
                    current.right.parent = current;
                    break;
                }
            }
        }
        return true;
    }

    public void remove(Node<T> n) {
        Node<T> p = n.parent;
        Node<T> next, child;

        // 叶子结点，直接删除即可。要考虑待删除结点是root的情况。
        if (n.left == null && n.right == null) {
            if (n == root) {
                root = null;
                return;
            }

            if (n == p.left)
                p.left = null;
            else if (n == p.right)
                p.right = null;
        }
        // 内部结点，把它的后继的值拷进来，然后递归删除它的后继。
        else if (n.left != null && n.right != null) {
            next = successor(n);
            n.data = next.data;
            remove(next);
        }
        // 只有一个孩子的结点，把它的孩子交给它的父结点即可。
        else {
            if (n.left != null)
                child = n.left;
            else
                child = n.right;

            if (n == root) {
                child.parent = null;
                root = child;
                return;
            }

            if (n == p.left) {
                child.parent = p;
                p.left = child;
            }
            else {
                child.parent = p;
                p.right = child;
            }
        }
    }

    public  void preOrder(Node<T> root) {
        Node<T> cur = root;
        if (cur == null) System.out.println("An empty tree!");
        else {
            System.out.print(cur.data);
            if (cur.left != null) preOrder(cur.left);
            if (cur.right != null) preOrder(cur.right);
        }
    }

    public  void midOrder(Node<T> root) {
        Node<T> cur = root;
        if (cur == null) System.out.println("An empty tree!");
        else {
            if (cur.left != null) midOrder(cur.left);
            System.out.print(cur.data);
            if (cur.right != null) midOrder(cur.right);
        }
    }

    public  void postOrder(Node<T> root) {
        Node<T> cur = root;
        if (cur == null) System.out.println("An empty tree!");
        else {
            if (cur.left != null) postOrder(cur.left);
            if (cur.right != null) postOrder(cur.right);
            System.out.print(cur.data);
        }

    }

    public void midOrderWithoutRecur(Node<T> root) {
        if (root == null)
            return;

        Stack<Node<T>> s = new Stack<>(64);
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

    public void preOrderWithoutRecur(Node<T> root) {
        if (root == null)
            return;

        Stack<Node<T>> s = new Stack<>(64);
        Node<T> current;

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

    public void LayerTraverse(Node<T> root) {
        if (root == null) return;

        Queue<Node<T>>q = new ArrayDeque<>();

        q.add(root);
        while (!q.isEmpty()) {
            Node<T> cur = q.poll();
            System.out.print(cur.data);
            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }

    public Node<T> successor(Node<T> n) {
        if (n == null)
            return null;

        Node<T> p;
        if (n.right != null) {
            p = n.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        else {
            p = n.parent;

            while (p != null && p.left != n) {
                n = p;
                p = n.parent;
            }
            return p;
        }
    }

    public Node<T> firstElement() {
        if (root == null) return null;

        Node<T> cur = root;
        while (cur.left != null)
            cur = cur.left;

        return cur;
    }

    public Iterator<Node<T>> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Node<T>> {
        private  Node<T> last;

        Itr() {
            last = firstElement();
        }
        @Override
        public boolean hasNext() {
            return last != null;
        }
        //第一次调用hasNext判断的也就是该集合类中是否有元素，而第一次调用next返回的也就是该集合类中的第一个元素
        @Override
        public Node<T> next() {
            Node<T> cur = last;
            last = successor(last);
            return cur;
        }
    }
}
