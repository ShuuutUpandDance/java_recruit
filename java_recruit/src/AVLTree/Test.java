package AVLTree;

/**
 * Created by Zhang on 2017/3/9.
 */
public class Test {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<Integer>();
        int[] nums = new int[]{20,10,5,30,40,15,25,23,50,1,3};
        //int[] nums = {5,4,3,2,1};
        for (int i = 0;i < nums.length;i++) {
            if (i == 0) {
                tree.root = new AVLNode<Integer>(nums[i]);
                tree.root.parent = null;
            }else
                tree.insert(tree.root,nums[i]);
        }

        int[] delete = new int[]{15,23,25,1,30,50,40,3};
        for (int num:delete) {
            tree.remove(tree.root,num);
        }



        tree.midOrder(tree.root);
        System.out.print("\n"+ "root: "+tree.root.data+"\n");
        System.out.print(tree.root.balance+"   "+ tree.root.depth);
    }
}
