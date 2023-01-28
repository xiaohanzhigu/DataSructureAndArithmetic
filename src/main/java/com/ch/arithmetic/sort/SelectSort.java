package com.ch.arithmetic.sort;

/**
 * 选择排序
 */
public class SelectSort extends SortTemplate{
    public static void main(String[] args) {
        new SelectSort().calculateTime();
    }

    @Override
    public void sort(int[] arr) {
        int minNumIndex;
        for (int i = 0; i < arr.length - 1; i++) {
            minNumIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minNumIndex]) {
                    minNumIndex = j;
                }
            }
            if (minNumIndex != i) {
                arr[minNumIndex] = arr[minNumIndex] ^ arr[i];
                arr[i] = arr[minNumIndex] ^ arr[i];
                arr[minNumIndex] = arr[minNumIndex] ^ arr[i];
            }
        }

    }
}
