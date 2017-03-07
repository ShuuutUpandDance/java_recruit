package generics;

/**
 * Created by Zhang on 2017/3/7.
 */
public class Main {
    public static void main(String[] args) {

    }
    public static <T extends Addable<T>> T add(T o1, T o2) {
        return o1.add(o2);
    }

}
interface Addable<T> {
    T add(T o1);
}
