package com.ch.arithmetic.sort;

public class InsertSort extends SortTemplate {
    public static void main(String[] args) {
        new InsertSort().testSort();
    }

    @Override
    public void sort(int[] arr) {
        int insertVal, insertValIndex;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i]; //要排序的数
            insertValIndex = i - 1; //要排序数的上一个元素的索引
            while (insertValIndex >= 0 && insertVal < arr[insertValIndex]) {
                arr[insertValIndex + 1] = arr[insertValIndex];
                insertValIndex--;
            }
            //循环结束代表找到要插入的位置
            arr[++insertValIndex] = insertVal;
        }
    }
}
