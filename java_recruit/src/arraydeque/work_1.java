package arraydeque;

/**
 * Created by Zhang on 2017/3/7.
 */
public class work_1 {
    int[] data;
    int head;
    int tail;

    public void addFirst(int element) {
        //先移动head指针
        //如果当前位置是0，就要移动到数组末端
        if (head == 0) head = data.length -1;
        else head --;
        if (head == tail) {
            //doubleCapacity
        }
        data[head] = element;
        /*实际上不用判断head当前是不是0了，因为如果是0的话，
        head-1变为-1，而-1的二进制为（全1）。因此（-1）&N=N
        自动回到数组末端。又是一处位操作的巧用。
        * */
        data[(head -= 1) & (data.length - 1)] = element;
        if (head == tail) {
            //doubleCapacity
        }
    }

    public void removeLast() {
        /* pos = (tail - 1) & (data.length - 1);
        E e = element[pos];
        if (e == null) throws exception; //空表情况
        element[pos] = null;
        top = pos;
        * */
    }

    public static void main(String[] args) {
        System.out.println((-1) & 100);
    }

}
