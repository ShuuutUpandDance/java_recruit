package binarytree;

/**
 * Created by Zhang on 2017/3/7.
 */
public class Node<T>{
    public T data;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;
    public int state;

    public Node(T d) {
        this.data = d;
    }
}
