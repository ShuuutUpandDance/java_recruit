package arraydeque;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * Created by Zhang on 2017/3/7.
 */
/*使用arraydeque比linkedlist快。应该是使用linkedlist时，
每次入队操作都需要新实例化一个节点，然后才能插入。而使用
arraydeque时，是对已经开辟好空间的数组直接赋值，仅在队列
满时需要double一下容量。因此arraydeque更快
* */
public class Main {
    public static void main(String args[]) {
        //Deque<Integer> q = new ArrayDeque<>();//took 17373228ns
        Deque<Integer> q = new LinkedList<>(); //took 35416085ns
        long begin = System.nanoTime();
        test(q);
        long end = System.nanoTime();
        System.out.println("took " + (end - begin) + "ns");
    }

    public static void test(Deque<Integer> q) {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 10_000; j++) {
                q.addLast(j);
            }

            for (int j = 0; j < 10_000; j++) {
                q.removeFirst();
            }
        }
    }
}
