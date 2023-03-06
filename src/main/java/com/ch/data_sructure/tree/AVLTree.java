package com.ch.data_sructure.tree;

import java.util.*;

public class AVLTree {
    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i : arr) {
            avlTree.add(i);
        }
        System.out.println(avlTree.infixOrder());
        System.out.println(avlTree.root.height());
        System.out.println(avlTree.root.leftHeight());
        System.out.println(avlTree.root.rightHeight());
    }


    private Node root;

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

        //返回左子树的高度
        public int leftHeight() {
            return left == null ? 0 : left.height();
        }

        //返回右子树的高度
        public int rightHeight() {
            return right == null ? 0 : right.height();
        }

        //返回以该节点为根节点树的高度
        public int height() {
            return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
        }

        public void rotate() {
            //如果 (右子树的高度 - 左子树的高度) > 1
            if ((rightHeight() - leftHeight()) > 1) {
                //如果它右子树的左子树高度大于它右子树的右子树高度
                if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                    //先对右子结点进行右旋转
                    this.right.rightRotate();
                }
                leftRotate();
                return;
            }

            if ((leftHeight() - rightHeight()) > 1) {
                //如果它左子树的右子树高度大于它左子树的左子树高度
                if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                    //先对左子节点进行做选择
                    this.left.leftRotate();
                }
                rightRotate();
            }
        }
        
        public void leftRotate() {
            //以当前根节点的值创建一个新的结点
            Node newNode = new Node(value);
            //把新结点的左子树设置为当前结点的左子树
            newNode.left = this.left;
            //把新节点的右子树设置为当前结点的右子树的左子树
            newNode.right = this.right.left;
            //把新节点的值设置为当前结点的右节点的值
            newNode.value = this.right.value;
            //把当前结点的右子树设置为当前结点的右子树的右子树
            this.right = this.right.right;
            //把当前结点的左子树设置为新节点
            this.left = newNode;
        }



        public void rightRotate() {
            //以当前根结点的值创建一个新的结点
            Node newNode = new Node(value);
            //把新结点的右子树设置为当前结点的右子树
            newNode.right = this.right;
            //把新结点的左子树设置为当前的左子树的右子树
            newNode.left = this.left.right;
            //把新结点的值设置为当前结点的左结点的值
            newNode.value = this.left.value;
            //把当前结点的左子树设置为当前结点的左子树的左子树
            this.left = this.left.left;
            //把当前结点的右子树设置为新的结点
            this.right = newNode;
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

            //添加完成后检查是否需要旋转
            rotate();
        }

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
