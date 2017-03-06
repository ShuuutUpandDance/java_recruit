package linked_list;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhang on 2017/3/6.
 */
/**
 * 复制一个单链表是简单的，只需按照旧链表的顺序开辟新的空间，next指针自然就连上了。
 * 但是复制一个复杂链表，还需要考虑sibling指针的问题，这是在按顺序创建节点时无法
 * 同步完成的事情，需要额外一个步骤。这里使用了一个map来存放复杂链表当前节点与
 * sibling组成的键值对，同时设置了一个按data值查询节点的方法。
 * 另外，在节点中添加了toString方法，方便输出节点信息验证正确性。
 */
public class ComplexLinkList {
    public ComplexLinkNode head;
    Map<String,String>map = new HashMap<>(); //存放当前节点与sibling的键值对

    public ComplexLinkList() {
        ComplexLinkNode nodeA = new ComplexLinkNode("A");
        ComplexLinkNode nodeB = new ComplexLinkNode("B");
        ComplexLinkNode nodeC = new ComplexLinkNode("C");
        ComplexLinkNode nodeD = new ComplexLinkNode("D");
        ComplexLinkNode nodeE = new ComplexLinkNode("E");
        nodeA.next = nodeB; nodeA.sibling = nodeD;
        nodeB.next = nodeC; nodeB.sibling = nodeC;
        nodeC.next = nodeD; nodeC.sibling = nodeE;
        nodeD.next = nodeE; nodeD.sibling = null;
        nodeE.next = null; nodeE.sibling = null;
        head = nodeA;

        //构造sibling的map
        ComplexLinkNode cur = head;
        while (cur != null) {
            if (cur.sibling == null) map.put(cur.data, "null");
            else map.put(cur.data,cur.sibling.data);
            cur = cur.next;
        }
    }

    public ComplexLinkNode findNode(String data) {
        ComplexLinkNode cur = head;
        while (cur.next != null) {
            if (cur.data == data)
                break;
            cur = cur.next;
        }
        return cur;
    }

    public ComplexLinkNode dup(ComplexLinkList list) {
        ComplexLinkNode head = new ComplexLinkNode(list.head.data);
        ComplexLinkNode listCur = list.head;
        ComplexLinkNode cur = head;
        while (listCur.next != null) {
            listCur = listCur.next;
            cur.next = new ComplexLinkNode(listCur.data);
            cur = cur.next;
        }
        cur.next = null;

        //连接sibling
        cur = head;
        while (cur != null) {
            if (map.get(cur.data) != "null")
                cur.sibling = findNode(map.get(cur.data));
            else cur.sibling = null;
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ComplexLinkList list = new ComplexLinkList();
        ComplexLinkNode dupListHead = list.dup(list);
        ComplexLinkNode cur = dupListHead;

        while (cur != null) {
            System.out.println(cur.toString());
            cur = cur.next;
        }
    }
}
