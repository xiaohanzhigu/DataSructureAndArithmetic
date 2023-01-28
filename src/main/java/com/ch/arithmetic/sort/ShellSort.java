package com.ch.arithmetic.sort;

/**
 * 希尔排序
 * 插入排序的扩展
 */
public class ShellSort extends SortTemplate{

    public static void main(String[] args) {
       new ShellSort().calculateTime();
    }

    @Override
    public void sort(int[] arr) {
        int insertVal, insertValIndex;
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {  //确定步长
            for (int i = gap; i < arr.length; i++) {
                insertValIndex = i;
                insertVal = arr[i];
                if (arr[insertValIndex] < arr[insertValIndex - gap]) {  //优化
                    while (insertValIndex - gap >= 0 && insertVal < arr[insertValIndex - gap]) {
                        arr[insertValIndex] = arr[insertValIndex - gap];
                        insertValIndex -= gap;
                    }
                    arr[insertValIndex] = insertVal;
                }
            }
        }
    }

    //交换法
    public void sort2(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j + gap];
                        arr[j + gap] = arr[j];
                        arr[j] = temp;
                    }
                }
            }

        }
    }
}
