package com.ch.data_sructure.tree;

import com.ch.common.Person;
import com.ch.common.TestUtil;

import java.util.List;

public class BinaryTree<T> {
    public static void main(String[] args) {
        BinaryTree<Person> binaryTree = new BinaryTree<>();
        List<Person> list = TestUtil.getPersonList();
        ArrayBinaryNode<Person> pn1 = new ArrayBinaryNode<>(1, list.get(0));
        ArrayBinaryNode<Person> pn2 = new ArrayBinaryNode<>(2, list.get(1));
        ArrayBinaryNode<Person> pn3 = new ArrayBinaryNode<>(3, list.get(2));
        ArrayBinaryNode<Person> pn4 = new ArrayBinaryNode<>(4, list.get(3));

        binaryTree.setRoot(pn1);
        pn1.setLeft(pn2);
        pn1.setRight(pn3);
        pn3.setRight(pn4);
        System.out.println("删除前");
        binaryTree.preOrder();
        binaryTree.remove(0);
        System.out.println("删除后");
        binaryTree.preOrder();
    }

    private ArrayBinaryNode<T> root;

    public T remove(int index) {
        ArrayBinaryNode<T> node = null;
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
            ArrayBinaryNode<T> node = root.preOrderSearch(index);
            if (node != null) {
                return node.getValue();
            }
        }
        return null;
    }

    public T infixOrderSearch(int index) {
        if (root != null) {
            ArrayBinaryNode<T> node = root.infixOrderSearch(index);
            if (node != null) {
                return node.getValue();
            }
        }
        return null;
    }

    public T postOrderSearch(int index) {
        if (root != null) {
            ArrayBinaryNode<T> node = root.postOrderSearch(index);
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

    public void setRoot(ArrayBinaryNode<T> root) {
        this.root = root;
    }
}

class ArrayBinaryNode<T> {
    private int no;
    private T value;
    private ArrayBinaryNode<T> root;
    private ArrayBinaryNode<T> left;
    private ArrayBinaryNode<T> right;

    public ArrayBinaryNode<T> removeNode(int index) {
        ArrayBinaryNode<T> node = null;
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

    public ArrayBinaryNode<T> preOrderSearch(int index) {
        if (this.no == index) {
            return this;
        }
        ArrayBinaryNode<T> result = null;
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

    public ArrayBinaryNode<T> infixOrderSearch(int index) {
        ArrayBinaryNode<T> result = null;
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
        return  result;
    }

    public ArrayBinaryNode<T> postOrderSearch(int index) {
        ArrayBinaryNode<T> result = null;
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
        System.out.println(this.no + " " +this.value);
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
        System.out.println(this.no + " " +this.value);
    }

    public ArrayBinaryNode() {

    }

    public ArrayBinaryNode(int no, T value) {
        this.no = no;
        this.value = value;
    }

    public int getNo() {
        return no;
    }

    public T getValue() {
        return value;
    }

    public ArrayBinaryNode<T> getRoot() {
        return root;
    }

    public ArrayBinaryNode<T> getLeft() {
        return left;
    }

    public ArrayBinaryNode<T> getRight() {
        return right;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setRoot(ArrayBinaryNode<T> root) {
        this.root = root;
    }

    public void setLeft(ArrayBinaryNode<T> left) {
        this.left = left;
    }

    public void setRight(ArrayBinaryNode<T> right) {
        this.right = right;
    }
}
