package com.my;

/**
 * 红黑树实现
 * 性质:
 * 1.每个节点具有颜色属性，要么为红色，要么为黑色
 * 2.根节点是黑色的
 * 3.每个叶子节点 (null) 是黑色的 (这里叶子节点，指为空的叶子节点)
 * 4.如果一个节点是红色的，则其子节点必须是黑色的
 * 5.从一个节点到该节点的叶节点 (null) 所有路径包含相同数目的黑节点
 *
 * @Author: zzk
 * @Date: 2020-04-16 14:05
 */
public class RedBlackTreeNode<T extends Comparable> {
    /**
     * 默认黑色
     *
     * @param val
     */
    public RedBlackTreeNode(T val) {
        this(val, false);
    }

    public RedBlackTreeNode(T val, boolean isRed) {
        this.isRed = isRed;
        this.val = val;
    }

    /**
     * 左子树
     */
    private RedBlackTreeNode<T> left;

    /**
     * 右子树
     */
    private RedBlackTreeNode<T> right;
    /**
     * 父节点
     */
    private RedBlackTreeNode<T> parent;

    /**
     * 节点值
     */
    private T val;

    /**
     * 是否为红节点
     */
    private boolean isRed;

    public boolean isBlack() {
        return !isRed();
    }

    public void setBlack() {
        this.setRed(false);
    }

    public void setRed() {
        this.setRed(true);
    }

    /**
     * 插入节点
     *
     * @param t
     */
    public void putVal(T t) throws Exception {
        // 构造一个红色节点
        RedBlackTreeNode<T> node = new RedBlackTreeNode<T>(val, true);
        // 1. 当做一颗二叉查找树插入节点
        insert(node);
        // 2. 将该节点着色为红色 (已提前做完)
        // 这里可能会违反性质4,因为其父节点可能是红色（如果一个节点是红色的，则其子节点必须是黑色的）
        // 3. 通过旋转和重新着色等方法修正该树，使之重新成为一棵红黑树
        balance(node);
    }

    /**
     * 移除节点
     *
     * @param node
     */
    public void remove(RedBlackTreeNode<T> node) {
        assert node != null;
        RedBlackTreeNode<T> parent = node.getParent();
        RedBlackTreeNode<T> left = node.getLeft();
        RedBlackTreeNode<T> right = node.getRight();
        boolean isLeftChild = parent.getLeft() == node;
        boolean isRightChild = !isLeftChild;
        // 删除节点为红色
        if (node.isRed()) {
            // 1.该节点为红色叶子节点,直接删除
            // 不存在删除的红色节点只有左子树或者右子树的情况，因为此情况违反性质5
            if (isLeaf()) {
                parent.removeChild(node);
            }
        } else {
            // 删除节点为黑色
            // 2. 如果删除的是黑色叶子节点
            // 兄弟节点
            RedBlackTreeNode<T> brother = parent.getLeft() == node ? parent.getRight() : parent.getLeft();
            RedBlackTreeNode<T> brotherLeftChild = brother != null ? brother.getLeft() : null;
            RedBlackTreeNode<T> brotherRightChild = brother != null ? brother.getRight() : null;
            // 远侄子
            RedBlackTreeNode<T> brotherFarChild = isLeftChild ? brotherRightChild : brotherLeftChild;
            // 近侄子
            RedBlackTreeNode<T> brotherNearChild = isRightChild ? brotherLeftChild : brotherRightChild;
            if (isLeaf()) {
                // 2.1：待删除节点D的兄弟节点S为红色
                if (brother != null && brother.isRed()) {
                    // 待删除节点为左子树
                    parent.setRed();
                    brother.setBlack();
                    if (node == parent.getLeft()) {
                        rotateLeft(parent);
                    } else {//待删除节点为右子树
                        rotateRight(parent);
                    }
                } else if (brotherFarChild != null && brotherFarChild.isRed()) {
                    // 2.2兄弟节点为黑色，且远侄子节点为红色。
                    swapColor(brother, parent);
                    if (isLeftChild) {
                        rotateLeft(parent);
                    } else {
                        rotateRight(parent);
                    }
                    brother.setBlack();
                } else if ((brotherFarChild == null || brotherFarChild.isBlack())
                        && brotherNearChild != null && brotherNearChild.isRed()) {
                    // 2.3兄弟节点S为黑色，远侄子节点为黑色，近侄子节点为红色
                    swapColor(brother, brotherNearChild);
                    if (isLeftChild) {
                        rotateRight(brotherNearChild);
                    } else {
                        rotateLeft(brotherNearChild);
                    }
                } else if (parent.isRed() && brother != null && brother.isBlack() && brother.isLeaf()) {
                    // 2.4 父亲节p为红色，兄弟节点和兄弟节点的两个孩子（只能是NULL节点）都为黑色的情况
                    parent.setBlack();
                    brother.setRed();
                } else {
                    // 2.5 父亲节点p，兄弟节点s和兄弟节点的两个孩子（只能为NULL节点）都为黑色的情况
                    assert brother != null;
                    brother.setRed();
                }
                parent.removeChild(node);
            } else if (left == null || right == null) {
                //3. 删除的节点只有左子树或者只有右子树,左/右子树根节点只能为红色，黑色违反红黑树性质5
                RedBlackTreeNode<T> tmp = null;
                if (left == null) {//只有右子树
                    tmp = node.getRight();
                } else {//只有左子树
                    tmp = node.getLeft();
                }
                // 替换节点
                parent.updateChild(node, tmp);
                tmp.parent.removeChild(tmp);
            } else {
                //4.删除的节点左右子树都有
                // 使用该节点的直接后继节点替换该节点，然后删除直接后继节点
                RedBlackTreeNode<T> next = getMiddleNext(node);
                // 替换节点
                node.setVal(next.val);
                // 删除直接后继节点
                next.parent.removeChild(next);
            }
        }
    }

    private void updateChild(RedBlackTreeNode<T> child, RedBlackTreeNode<T> newChild) {
        if (this.getRight() == child) {
            parent.setRight(newChild);
        } else {
            parent.setLeft(newChild);
        }
    }

    public static void swapColor(RedBlackTreeNode a, RedBlackTreeNode b) {
        boolean aColor = a.isRed();
        a.setRed(b.isRed());
        b.setRed(aColor);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * 删除子节点
     *
     * @param node
     */
    public void removeChild(RedBlackTreeNode<T> node) {
        if (this.left == node) {
            this.setLeft(null);
        } else {
            this.setRight(null);
        }
    }

    /**
     * 通过旋转和重新着色等方法修正该树，使之重新成为一棵红黑树
     *
     * @param x 被插入的节点
     */
    public void balance(RedBlackTreeNode<T> x) {
        RedBlackTreeNode<T> xp = x.getParent();
        // 1.被插入的节点是根节点
        if (xp == null) {
            // 将根节点变为黑色
            x.setBlack();
        } else if (xp.isBlack()) {
            //被插入节点的父节点是红色,
            // 2.被插入的节点的父节点是红色，违反规则4:如果一个节点是红色的，则其子节点必须是黑色的
            // 祖父节点
            RedBlackTreeNode<T> xpp = xp.getParent();
            RedBlackTreeNode<T> xppl = xpp.left;
            RedBlackTreeNode<T> xppr = xpp.right;
            // 叔叔节点
            RedBlackTreeNode<T> uncleNode = xppl == xp ? xppr : xppl;
            // 3.如果叔叔节点为红色
            if (uncleNode != null && uncleNode.isRed()) {
                xpp.setRed();
                uncleNode.setBlack();
                xp.setBlack();
                // 对祖父节点作为当前节点 重新进行平衡操作
                balance(xpp);
            } else if (uncleNode == null || uncleNode.isBlack()) {
                // 4.叔叔节点为黑色或缺失，且当前节点是曲线边 (即左右或右左)
                // 如果是曲线边
                if (xppl != null && xppl.getRight() == x) {
                    rotateLeft(xp);
                    balance(xp);
                } else if (xppr != null && xppr.getLeft() == x) {
                    rotateRight(xp);
                    balance(xp);
                } else {
                    // 5.叔叔节点为黑色或缺失，且当前节点是在外边(即左左或右右)
                    xp.setBlack();
                    xpp.setRed();
                    rotateRight(xpp);
                }
            }
        }
    }

    /**
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *      px                              px
     *     /                               /
     *    x                               y
     *   /  \      --(左旋)-.           / \                #
     *  lx   y                          x  ry
     *     /   \                       /  \
     *    ly   ry                     lx  ly
     *
     */
    public static <T extends Comparable> void rotateLeft(RedBlackTreeNode<T> x) {
        RedBlackTreeNode<T> y = x.getRight();
        // 将右节点的左孩子设置为x的右节点
        RedBlackTreeNode<T> yl = y.getLeft();
        if (yl != null) {
            x.setRight(yl);
        }
        // 将 “x的父亲” 设为 “y的父亲”
        RedBlackTreeNode<T> px = x.getParent();
        y.setParent(px);
        if (px == null) {
            // 如果 “x的父亲” 是空节点，则将y设为根节点
            x.setParent(y);
        } else {
            if (px.getLeft() == x) {
                // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
                px.setLeft(y);
            } else {
                // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
                px.setRight(y);
            }
        }
        // 将 “x” 设为 “y的左孩子”
        y.setLeft(x);
        // 将 “x的父节点” 设为 “y”
        x.setParent(y);
    }

    /**
     * 右旋
     * 对红黑树的节点(y)进行右旋转
     *
     * 右旋示意图(对节点y进行左旋)：
     *            py                               py
     *           /                                /
     *          y                                x
     *         /  \      --(右旋)-.            /  \                     #
     *        x   ry                           lx   y
     *       / \                                   / \                   #
     *      lx  rx                                rx  ry
     */
    public void rotateRight(RedBlackTreeNode<T> y) {
        // 设置x是当前节点的左孩子。
        RedBlackTreeNode<T> x = y.getLeft();
        y.setLeft(x.getRight());
        if (x.getRight() != null) {
            x.getRight().setParent(y);
        }
        // 将 “x的右孩子” 设为 “y的左孩子”；
        // 如果"x的右孩子"不为空的话，将 “y” 设为 “x的右孩子的父亲”
        RedBlackTreeNode<T> yp = y.getParent();
        x.setParent(yp);

        if (yp == null) {
            // 如果 “y的父亲” 是空节点，则将x设为根节点
            y.setParent(x);
        } else {
            if (y == yp.getRight()) {
                // 如果 y是它父节点的右孩子，则将x设为“y的父节点的右孩子”
                yp.setRight(x);
            }else {
                // (y是它父节点的左孩子) 将x设为“x的父节点的左孩子”
                yp.setLeft(x);
            }
        }
        // 将 “y” 设为 “x的右孩子”
        x.setRight(y);
        // 将 “y的父节点” 设为 “x”
        y.setParent(x);
    }

    /**
     * 按照二叉查找树的规则插入节点
     *
     * @param node
     * @throws Exception
     */
    private void insert(RedBlackTreeNode<T> node) throws Exception {
        assert node != null && node.getVal() != null;
        int comp;
        RedBlackTreeNode<T> cur = this, l = left, r = right;
        while (cur != null) {
            comp = node.getVal().compareTo(cur.val);
            if (comp == 0) {
                throw new Exception("重复的节点值!");
            }
            // 大于当前节点，插入右子树,小于插入左子树
            if (comp > 0) {
                if (r != null) {
                    cur = cur.getRight();
                } else {
                    cur.setRight(node);
                    break;
                }
            } else if (l != null) {
                cur = cur.getLeft();
            } else {
                cur.setLeft(node);
                break;
            }
        }
        node.setParent(cur);
    }

    /**
     * 按照二叉查找树的规则删除节点
     *
     * @param node
     */
    private void delete(RedBlackTreeNode<T> node) {
        RedBlackTreeNode<T> l = node.getLeft();
        RedBlackTreeNode<T> r = node.getRight();
        RedBlackTreeNode<T> parent = node.getParent();
        if (parent == null) {
            return;
        } else {
            RedBlackTreeNode<T> tmp;
            if (l == null) {// 左子树为空
                tmp = r;
            } else if (r == null) {//右子树为空
                tmp = l;
            } else {//左右子树都不为空
                //需要在树中找出所有比被删除节点的值大的所有节点的最小值的节点
                RedBlackTreeNode<T> next = getMiddleNext(node);
                next.setLeft(l);
                next.getParent().setLeft(next.getRight());
                next.setRight(r);
                tmp = next;
            }
            if (parent.getLeft() == node) {
                parent.setLeft(tmp);
            } else {
                parent.setRight(tmp);
            }
        }
    }

    /**
     * 获取node节点中序遍历的下一个节点
     *
     * @param node
     * @return
     */
    public RedBlackTreeNode<T> getMiddleNext(RedBlackTreeNode<T> node) {
        RedBlackTreeNode<T> r = node.getRight();
        if (r == null) return null;
        r = r.getLeft();
        while (r != null) {
            r = r.getLeft();
        }
        return r;
    }

    public RedBlackTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(RedBlackTreeNode<T> left) {
        this.left = left;
    }

    public RedBlackTreeNode<T> getRight() {
        return right;
    }

    public void setRight(RedBlackTreeNode<T> right) {
        this.right = right;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public void setParent(RedBlackTreeNode<T> parent) {
        this.parent = parent;
    }

    public RedBlackTreeNode<T> getParent() {
        return parent;
    }
}
