package com.ch.arithmetic.sort;

/**
 * 冒泡排序
 */
public class BubbleSort extends SortTemplate {
    public static void main(String[] args) {
        new BubbleSort().calculateTime();
    }

    @Override
    public void sort(int[] arr) {
        int temp, count = 0;
        boolean flag = false;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                count++;
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
