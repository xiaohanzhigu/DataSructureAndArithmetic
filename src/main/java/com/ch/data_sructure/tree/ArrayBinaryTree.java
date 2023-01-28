package com.ch.data_sructure.tree;

public class ArrayBinaryTree {
    public static void main(String[] args) {
        new ArrayBinaryTree().preOrder(0);
    }

    private int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};

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
