package com.ch.data_sructure.tree;

/**
 * 顺序存储二叉树的特点:
 *  顺序二叉树通常只考虑完全二叉树
 *  第n个元素的左子节点为  2 * n + 1
 *  第n个元素的右子节点为  2 * n + 2
 *  第n个元素的父节点为  (n-1) / 2
 */
public class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree() {
        arr = new int[]{1, 2, 3, 4, 5, 6, 7};
    }

    public ArrayBinaryTree(int[] arr) {
       this.arr = arr;
    }

    public void preOrder() {
        preOrder(0);
    }

    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
        }
        System.out.println(arr[index]);
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }
}
