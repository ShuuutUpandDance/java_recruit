package linked_list;

/**
 * Created by Zhang on 2017/3/6.
 */
public class DoubleLinkNode {
    public int data;
    public DoubleLinkNode next;
    public DoubleLinkNode prev;

    public DoubleLinkNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
