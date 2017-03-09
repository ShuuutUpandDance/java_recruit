package AVLTree;


/**
 * Created by Zhang on 2017/3/9.
 */
public class AVLTree <T extends Comparable<T>>{
    public AVLNode<T> root ;

    /**
     * 求n的中序后继
     * @param n
     * @return
     */
    private AVLNode<T> successor(AVLNode<T> n) {
        if (n == null)
            return null;

        AVLNode<T> p;
        if (n.right != null) {
            p = n.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        else {
            p = n.parent;

            while (p != null && p.left != n) {
                n = p;
                p = n.parent;
            }
            return p;
        }
    }
    /**
     * 指定根节点，递归删除
     * @param root
     */
    public void remove(AVLNode<T> root, T data) {
        AVLNode<T> p = root.parent;
        AVLNode<T> next,child;
        if (data.compareTo(root.data) < 0) {
            if (root.left != null)
                remove(root.left,data);
            else return;
        } else if (data.compareTo(root.data) > 0){
            if (root.right != null)
                remove(root.right,data);
            else return;
        } else {
            //叶子节点，直接删除
            if (root.left == null && root.right == null) {
                if (root == this.root) {
                    this.root = null;
                    return;
                } else if (root == p.left) {
                    p.left = null;
                    adjust(p);
                } else {
                    p.right = null;
                    adjust(p);
                }
            }
            //内部节点，把中序后继考进来，递归删除后继
            else if (root.left != null && root.right != null) {
                next = successor(root);
                root.data = next.data;
                remove(next,data);
            }
            else { //只有一个孩子
                if (root.left != null)
                    child = root.left;
                else
                    child = root.right;

                if (root == this.root) {
                    child.parent = null;
                    this.root = child;
                    adjust(this.root);
                } else if (root == p.left){
                    p.left = child;
                    adjust(p);
                } else if (root == p.right) {
                    p.right = child;
                    adjust(p);
                }
            }
        }
    }
    public  void midOrder(AVLNode<T> root) {
        AVLNode<T> cur = root;
        if (cur == null) System.out.println("An empty tree!");
        else {
            if (cur.left != null) midOrder(cur.left);
            System.out.print(cur.data+" ");
            if (cur.right != null) midOrder(cur.right);
        }
    }
    /**
     * 指定根节点，递归插入
     * @param root
     * @param data
     */
    public void insert(AVLNode<T> root, T data) {
        if (data.compareTo(root.data)<0) {
            if (root.left != null)
                insert(root.left, data);
            else {
                root.left = new AVLNode(data);
                root.left.parent = root;
            }
        }
        else {
            if (root.right != null)
                insert(root.right, data);
            else {
                root.right = new AVLNode(data);
                root.right.parent = root;
            }
        }
        /* 这里要做一些特殊的处理了 */
        /* 从插入的过程回溯回来的时候，计算平衡因子 */
        root.balance = calcBalance(root);

	/* 左子树高，应该右旋 */
        if (root.balance >= 2) {
	    /* 右孙高，先左旋 */
            if (root.left.balance == -1)
                left_rotate(root.left);

            right_rotate(root);
        }

        if (root.balance <= -2)
        {
            if (root.right.balance == 1)
                right_rotate(root.right);

            left_rotate(root);
        }

        root.balance = calcBalance(root);
        root.depth = calcDepth(root);
    }

    /**
     * 调整平衡
     * @param root
     */
    private void adjust(AVLNode<T> root) {
         /*AVL树的特殊处理*/
        root.balance = calcBalance(root);

        //左高，则右旋
        if (root.balance >= 2) {
            //右孙高，先左旋左子
            if (root.left.depth == -1)
                left_rotate(root.left);

            right_rotate(root);
        }

        //反之
        if (root.balance <= -2) {
            if (root.right.balance == 1)
                right_rotate(root.right);

            left_rotate(root);
        }
        root.balance = calcBalance(root);
        root.depth = calcDepth(root);
    }

    /**
     * 以p为轴，左旋。口诀：右子作父，父为左子，左孙变右孙
     * @param p
     */
    private void left_rotate(AVLNode<T> p) {
        System.out.println("左旋");
        AVLNode<T> pParent = p.parent, pRightSon = p.right;
        AVLNode<T> pLeftGrandSon = pRightSon.left;

        //右子作父+父为左子
        pRightSon.parent = pParent;
        if (pParent != null) {
            if (p == pParent.left)
                pParent.left = pRightSon;
            else if (p == pParent.right)
                pParent.right = pRightSon;
            pRightSon.left = p;
            p.parent = pRightSon;
        } else {
            root = pRightSon;
            pRightSon.left = p;
            pRightSon.parent = null;
            p.parent = pRightSon;
        }

        //左孙变右孙
        p.right = pLeftGrandSon;
        if (pLeftGrandSon != null) {
            pLeftGrandSon.parent = p;
        }

         /* 重新计算平衡因子 */
        p.depth = calcDepth(p);
        p.balance = calcBalance(p);

        pRightSon.depth = calcDepth(pRightSon);
        pRightSon.balance = calcBalance(pRightSon);
    }

    /**
     * 以p为轴，右旋。口诀：左子作父，父作右子，右孙变左孙
     * @param p
     */
    private void right_rotate(AVLNode<T> p) {
        System.out.println("右旋");
           /* 一次旋转涉及到的结点包括祖父，父亲，右儿子 */
        AVLNode pParent = p.parent, pRightSon = p.left;
        AVLNode pLeftGrandSon = pRightSon.right;

	    /* 左子僭为父 +父为右子*/
        pRightSon.parent = pParent;
        if (pParent != null) {
            if (p == pParent.left)
                pParent.left = pRightSon;
            else if (p == pParent.right)
                pParent.right = pRightSon;
            pRightSon.right = p;
            p.parent = pRightSon;
        } else {
            root = pRightSon;
            pRightSon.right = p;
            pRightSon.parent = null;
            p.parent = pRightSon;
        }


	    /* 右孙变左孙 */
        p.left = pLeftGrandSon;
        if (pLeftGrandSon != null)
            pLeftGrandSon.parent = p;

	    /* 重新计算平衡因子 */
        p.depth = calcDepth(p);
        p.balance = calcBalance(p);

        pRightSon.depth = calcDepth(pRightSon);
        pRightSon.balance = calcBalance(pRightSon);
    }

    /**
     * 计算p节点的平衡度
     * @param p
     * @return
     */
    private int calcBalance(AVLNode<T> p) {
        int left_depth;
        int right_depth;

        if (p.left != null)
            left_depth = p.left.depth;
        else
            left_depth = 0;

        if (p.right != null)
            right_depth = p.right.depth;
        else
            right_depth = 0;

        return left_depth - right_depth;
    }

    /**
     * 以p为根，计算树的深度
     * 因为每次做插入、删除、旋转都计算了深度
     * 所以此处不需递归计算
     * @param p
     * @return
     */
    private int calcDepth(AVLNode<T> p){
        int depth = 0;
        if (p.left != null)
            depth = p.left.depth;

        if (p.right != null && depth < p.right.depth)
            depth = p.right.depth;

        depth++;
        return depth;
    }
}
