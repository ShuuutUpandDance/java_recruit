package linked_list;

/**
 * Created by Zhang on 2017/3/6.
 */
public class LinkList {  //作业2
    public LinkNode head;
    public static void main(String[] args) {
       LinkList list = new LinkList();
       list.head = new LinkNode(1);

       LinkNode cur = list.head;
        for (int i = 2; i <= 20; i++) {
            cur.next = new LinkNode(i);
            cur = cur.next;
        }
        cur.next = null;

        cur = list.head.next;
        LinkNode pre = list.head;
        while (cur != null) {   //指定删除data为偶数的节点
            if ((cur.data & 1) == 0) {
                list.deleteNode(cur);
                cur = pre.next;
            }
            if (cur == null) break;
            cur = cur.next;
            pre = pre.next;
        }

        cur = list.head;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }
    }

    public LinkNode findPreviousNode(LinkNode toBeFound) {
        LinkNode cur = head;
        if (cur == toBeFound) return null; //就是头节点，没有pre
        while (cur.next != toBeFound) cur = cur.next;
        return cur;
    }

    public void deleteNode(LinkNode toBeDelete) {
       LinkNode pre = findPreviousNode(toBeDelete);

       if (pre == null && toBeDelete.next == null) { //此时为单一节点链表
           head = null;
       } else if (pre == null) { //此时为头结点
           head = head.next;
       } else { //其他情况
           pre.next = toBeDelete.next;
       }
    }

}
