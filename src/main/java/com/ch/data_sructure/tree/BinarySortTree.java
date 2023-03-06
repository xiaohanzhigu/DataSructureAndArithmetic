package com.ch.data_sructure.tree;

import com.ch.common.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉排序树：BST: (Binary Sort(Search) Tree)
 * 对于二叉排序树的任何一个非叶子节点
 * 要求左子节点的值比当前节点的值小，右子节点的值比当前节点的值大。
 * 特别说明：如果有相同的值，可以将该节点放在左子节点或右子节点
 */
public class BinarySortTree {
    public static void main(String[] args) {
        BinarySortTree sortTree = new BinarySortTree();
        int[] arr = new int[]{7, 3, 10, 12, 5, 1, 9, 2};
        //int[] arr = TestUtil.randomNumArr();
        for (int i : arr) {
            sortTree.add(i);
        }
        System.out.println(sortTree.infixOrder());
        //sortTree.remove(7);
        //System.out.println(sortTree.infixOrder());

        for (int i : arr) {
            sortTree.remove(i);
            System.out.println(sortTree.infixOrder());
        }

    }

    private Node root;

    public BinarySortTree() {
    }

    public void remove(int value) {
        if (root == null) {
            return;
        }
        if (root.value == value) {
            if (root.left == null && root.right == null) {
                root = null;
            } else {
                int min = root.removeRightTreeMinNumber(root);
                root.value = min;
            }
        } else {
            root.remove(value);
        }
    }

    public void add(int value) {
        Node node = new Node(value);
        if (root == null) {
            this.root = node;
        } else {
            root.addNode(node);
        }
    }

    public List<Integer> infixOrder() {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            root.infixOrder(list);
        }
        return list;
    }

    public List<Integer> perOrder() {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            root.perOrder(list);
        }
        return list;
    }

    @Override
    public String toString() {
        return perOrder().toString();
    }

    class Node {
        int value;
        Node left;
        Node right;

        public Node remove(int value) {
            if (this.left != null && this.left.value == value) {
                return removeNode(this, this.left);
            }
            if (this.right != null && this.right.value == value) {
                return removeNode(this, this.right);
            }
            if (value < this.value && this.left != null) { //查找左子树
                this.left.remove(value);
            } else if (this.right != null) { //查找右子树
                this.right.remove(value);
            }
            return null;
        }

        /*
                删除结点
                删除叶子节点 (比如：2, 5, 9, 12)
                删除只有一颗子树的节点 (比如：1)
                删除有两颗子树的节点. (比如：7, 3，10 ) */
        public Node removeNode(Node parent, Node target) {
            if (target.left == null && target.right == null) { //删除叶子节点
                if (parent.left == target) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if (target.left != null && target.right != null) { //删除有两颗子树的节点
                int min = removeRightTreeMinNumber(target);
                target.value = min;
            } else if (parent.left == target) { //删除只有一颗子树的节点
                if (target.left != null) {
                    parent.left = target.left;
                } else {
                    parent.left = target.right;
                }
            } else if (parent.right == target) {
                if (target.left != null) {
                    parent.right = target.left;
                } else {
                    parent.right = target.right;
                }
            }
            return null;
        }

        /**
         * 1. 返回的 以node 为根结点的二叉排序树的最小结点的值
         * 2. 删除node 为根结点的二叉排序树的最小结点
         *
         * @param node 当前结点
         * @return 右子树最小的结点
         */
        private int removeRightTreeMinNumber(Node node) {
            Node target = node.right;
            if (target == null) {
                target = node.left;
            }
            //循环的查找左子节点，就会找到最小值
            while (target.left != null) {
                target = target.left;
            }
            //这时 target就指向了最小结点
            //删除最小结点
            remove(target.value);
            return target.value;
        }

        //添加结点
        private void addNode(Node node) {
            if (node == null)
                return;
            if (node.value < this.value) { //如果小于就插入左结点
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.addNode(node);
                }
            } else {
                if (this.right == null) { ////如果大于就插入右结点
                    this.right = node;
                } else {
                    this.right.addNode(node);
                }
            }

        }

        public void infixOrder(List<Integer> list) {
            if (this.left != null) {
                this.left.infixOrder(list);
            }
            list.add(value);
            if (this.right != null) {
                this.right.infixOrder(list);
            }
        }

        public void perOrder(List<Integer> list) {
            list.add(value);
            if (this.left != null) {
                this.left.perOrder(list);
            }
            if (this.right != null) {
                this.right.perOrder(list);
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        public void Node() {
        }

        public Node(int value) {
            this.value = value;
        }
    }
}
