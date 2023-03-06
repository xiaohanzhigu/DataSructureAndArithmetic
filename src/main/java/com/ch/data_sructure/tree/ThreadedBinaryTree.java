package com.ch.data_sructure.tree;

import com.ch.common.Person;
import com.ch.common.TestUtil;

import java.util.List;

public class ThreadedBinaryTree<T> {
    public static void main(String[] args) {
        ThreadedBinaryTree<Person> tree = new ThreadedBinaryTree<>();
        List<Person> list = TestUtil.getPersonList();
        ThreadedBinaryTreeNode<Person> pn1 = new ThreadedBinaryTreeNode<>(1, list.get(0));
        ThreadedBinaryTreeNode<Person> pn8 = new ThreadedBinaryTreeNode<>(8, list.get(1));
        ThreadedBinaryTreeNode<Person> pn3 = new ThreadedBinaryTreeNode<>(3, list.get(2));
        ThreadedBinaryTreeNode<Person> pn10 = new ThreadedBinaryTreeNode<>(10, list.get(3));
        ThreadedBinaryTreeNode<Person> pn14 = new ThreadedBinaryTreeNode<>(14, list.get(4));
        ThreadedBinaryTreeNode<Person> pn6 = new ThreadedBinaryTreeNode<>(6, list.get(5));

        pn1.setLeft(pn3);
        pn3.setLeft(pn8);
        pn3.setRight(pn10);
        pn1.setRight(pn6);
        pn6.setRight(pn14);

        tree.setRoot(pn1);
        tree.threadedNode();
        tree.threadedList();
    }



    private ThreadedBinaryTreeNode<T> root;
    private ThreadedBinaryTreeNode pre = null; //在递归进行线索化时，pre 总是保留前一个结点

    public void threadedNode() {
        threadedNode(root);
        pre = null;
    }

    /**
     * 遍历线索化二叉树
     */
    public void threadedList() {
        ThreadedBinaryTreeNode node = root;

        while (node != null) {
            //找到第一个线索化的节点，也就是找到开始的位置
            while (node.getLeftType() != 1) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    /**
     * 线索化节点
     * @param node 要线索化的节点
     */
    public void threadedNode(ThreadedBinaryTreeNode node) {
        if (node == null) {
            return;
        }

        //(一)线索化左子树
        threadedNode(node.getLeft());
        //(二)线索化当前节点

        //处理当前结点的前驱结点
        if (node.getLeft() == null) {
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继结点
        if (pre != null && pre.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            pre.setRightType(1);
        }

        //设置当前节点为前驱结点
        pre = node;
        //(三)线索化右子树
        threadedNode(node.getRight());
    }

    public T remove(int index) {
        ThreadedBinaryTreeNode<T> node = null;
        if (root != null) {
            if (root.getNo() == index) {
                node = root;
                root = null;
            } else {
                node = root.removeNode(index);
            }
        }
        return node == null ? null : node.getValue();
    }

    public T preOrderSearch(int index) {
        if (root != null) {
            ThreadedBinaryTreeNode<T> node = root.preOrderSearch(index);
            if (node != null) {
                return node.getValue();
            }
        }
        return null;
    }

    public T infixOrderSearch(int index) {
        if (root != null) {
            ThreadedBinaryTreeNode<T> node = root.infixOrderSearch(index);
            if (node != null) {
                return node.getValue();
            }
        }
        return null;
    }

    public T postOrderSearch(int index) {
        if (root != null) {
            ThreadedBinaryTreeNode<T> node = root.postOrderSearch(index);
            if (node != null) {
                return node.getValue();
            }
        }
        return null;
    }

    public void preOrder() {
        if (root != null) {
            root.preOrder();
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        }
    }

    public void setRoot(ThreadedBinaryTreeNode<T> root) {
        this.root = root;
    }

}

class ThreadedBinaryTreeNode<T> {
    private int no;
    private T value;
    private ThreadedBinaryTreeNode<T> left;
    private ThreadedBinaryTreeNode<T> right;
    //1. 如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    //2. 如果rightType == 0 表示指向是右子树, 如果 1表示指向后继结点
    private int leftType;
    private int rightType;

    public ThreadedBinaryTreeNode<T> removeNode(int index) {
        ThreadedBinaryTreeNode<T> node = null;
        if (this.left != null && this.left.getNo() == index) {
            node = this.left;
            this.left = null;
            return node;
        }
        if (this.right != null && this.right.getNo() == index) {
            node = this.right;
            this.right = null;
            return node;
        }
        if (this.left != null) {
            this.left.removeNode(index);
        }
        if (this.right != null) {
            this.right.removeNode(index);
        }
        return null;
    }

    public ThreadedBinaryTreeNode<T> preOrderSearch(int index) {
        if (this.no == index) {
            return this;
        }
        ThreadedBinaryTreeNode<T> result = null;
        if (this.left != null) {
            result = this.left.preOrderSearch(index);
        }
        if (result != null) {
            return result;
        }
        if (this.right != null) {
            result = this.right.preOrderSearch(index);
        }
        return result;
    }

    public ThreadedBinaryTreeNode<T> infixOrderSearch(int index) {
        ThreadedBinaryTreeNode<T> result = null;
        if (this.left != null) {
            result = this.left.infixOrderSearch(index);
        }
        if (result != null) {
            return result;
        }
        if (this.no == index) {
            return this;
        }
        if (this.right != null) {
            result = this.right.infixOrderSearch(index);
        }
        return result;
    }

    public ThreadedBinaryTreeNode<T> postOrderSearch(int index) {
        ThreadedBinaryTreeNode<T> result = null;
        if (this.left != null) {
            result = this.left.postOrderSearch(index);
        }
        if (result != null) {
            return result;
        }
        if (this.no == index) {
            return this;
        }
        if (this.right != null) {
            result = this.right.postOrderSearch(index);
        }
        return result;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this.no + " " + this.value);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this.no + " " + this.value);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this.no + " " + this.value);
    }

    public ThreadedBinaryTreeNode(int no, T value) {
        this.no = no;
        this.value = value;
    }

    @Override
    public String toString() {
        String leftNo, rightNo;
        if (left == null) {
            leftNo = "null";
        } else {
            leftNo = Integer.toString(left.getNo());
        }
        if (right == null) {
            rightNo = "null";
        } else {
            rightNo = Integer.toString(right.getNo());
        }

        return "Node{" +
                "no=" + no +
                ", value=" + value.toString() +
                ", left=" + leftNo +
                ", right=" + rightNo +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }

    public ThreadedBinaryTreeNode() {}

    public ThreadedBinaryTreeNode(int no, T value, ThreadedBinaryTreeNode<T> left, ThreadedBinaryTreeNode<T> right, int leftType, int rightType) {
        this.no = no;
        this.value = value;
        this.left = left;
        this.right = right;
        this.leftType = leftType;
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ThreadedBinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(ThreadedBinaryTreeNode<T> left) {
        this.left = left;
    }

    public ThreadedBinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(ThreadedBinaryTreeNode<T> right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}


