package linked_list;

/**
 * Created by Zhang on 2017/3/6.
 */
public class ComplexLinkNode {
    String data;
    ComplexLinkNode next;
    ComplexLinkNode sibling;

    public ComplexLinkNode(String data) {
        this.data = data;
        next = null;
        sibling = null;
    }

    public String toString() {
        String ss = "Node "+data+";";
        if (next == null) ss += " next: null;";
        else ss += " next: "+next.data+";";
        if (sibling == null) ss += " sibling: null.";
        else ss += " sibling: "+sibling.data+".";

        return ss;
    }
}
