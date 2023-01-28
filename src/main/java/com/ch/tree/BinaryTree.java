package com.ch.tree;

import com.ch.common.Person;
import com.ch.common.TestUtil;

import java.util.List;

public class BinaryTree<T> {
    public static void main(String[] args) {
        BinaryTree<Person> binaryTree = new BinaryTree<>();
        List<Person> list = TestUtil.getPersonList();
        Node<Person> pn1 = new Node<>(1, list.get(0));
        Node<Person> pn2 = new Node<>(2, list.get(1));
        Node<Person> pn3 = new Node<>(3, list.get(2));
        Node<Person> pn4 = new Node<>(4, list.get(3));

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

    private Node<T> root;

    public T remove(int index) {
        Node<T> node = null;
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
            Node<T> node = root.preOrderSearch(index);
            if (node != null) {
                return node.getValue();
            }
        }
        return null;
    }

    public T infixOrderSearch(int index) {
        if (root != null) {
            Node<T> node = root.infixOrderSearch(index);
            if (node != null) {
                return node.getValue();
            }
        }
        return null;
    }

    public T postOrderSearch(int index) {
        if (root != null) {
            Node<T> node = root.postOrderSearch(index);
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

    public void setRoot(Node<T> root) {
        this.root = root;
    }
}

class Node<T> {
    private int no;
    private T value;
    private Node<T> root;
    private Node<T> left;
    private Node<T> right;

    public Node<T> removeNode(int index) {
        Node<T> node = null;
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

    public Node<T> preOrderSearch(int index) {
        if (this.no == index) {
            return this;
        }
        Node<T> result = null;
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

    public Node<T> infixOrderSearch(int index) {
        Node<T> result = null;
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

    public Node<T> postOrderSearch(int index) {
        Node<T> result = null;
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

    public Node() {

    }

    public Node(int no, T value) {
        this.no = no;
        this.value = value;
    }

    public int getNo() {
        return no;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getRoot() {
        return root;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}
