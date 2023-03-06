package com.ch.data_sructure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 赫夫曼树
 * 1、从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
 * 2、取出根节点权值最小的两颗二叉树
 * 3、组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 4、再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复  1-2-3-4 的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();
        Node root = huffmanTree.createHuffmanTree(new int[]{13, 7, 8, 3, 29, 6, 1});
        root.perOrder();
    }

    public Node createHuffmanTree(int[] arr) {
        List<Node> list = new ArrayList<>();
        for (int value : arr) {
            list.add(new Node(value));
        }

        while (list.size() > 1) {
            //1、从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
            Collections.sort(list);

            //2、取出根节点权值最小的两颗二叉树
            Node left = list.get(0);
            Node right = list.get(1);

            //3、组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
            Node parent = new Node(left.getValue() + right.getValue());
            parent.setLeft(left);
            parent.setRight(right);

            //4、再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复  1-2-3-4 的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树
            list.remove(1);
            list.remove(0);
            list.add(parent);
        }
        return list.get(0);
    }

    public void perOrder(Node root) {
        if (root != null) {
            root.perOrder();
        }
    }

}

class Node implements Comparable<Node>{
    private int value;
    private Node left;
    private Node right;

    public void perOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.perOrder();
        }
        if (this.right != null) {
            this.right.perOrder();
        }
    }


    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (value != node.value) return false;
        if (!Objects.equals(left, node.left)) return false;
        return Objects.equals(right, node.right);
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    public Node() {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

