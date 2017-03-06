package linked_list;

/**
 * Created by Zhang on 2017/3/6.
 */
public class DoubleLinkList {
    public DoubleLinkNode head;
    public DoubleLinkNode tail;
    public DoubleLinkNode queryNode(int index){// 查询第 index 项的内容
        DoubleLinkNode cur = head;
        for (int i = 1; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }
    public void deleteNode(DoubleLinkNode toBeDelete){// 将 toBeDelete 从链表中删除
        DoubleLinkNode cur = head;
        while (cur.next != null) {
            if(cur.next == toBeDelete) {
                cur = cur.next;
                break;
            }
        }
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
    }
    public void insertNode(DoubleLinkNode pos, DoubleLinkNode toBeInsert){// 将toBeInsert插入到pos结点后面
        DoubleLinkNode cur = head;
        while (cur.next != null) {
            if(cur.next == pos) {
                cur = cur.next;
                break;
            }
        }
        pos.next.prev = toBeInsert;
        toBeInsert.prev = pos;
        toBeInsert.next = pos.next;
        pos.next = toBeInsert;
    }
}
