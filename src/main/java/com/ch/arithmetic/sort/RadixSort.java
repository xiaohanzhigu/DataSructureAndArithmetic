package com.ch.arithmetic.sort;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort extends SortTemplate{
    public static void main(String[] args) {
     new RadixSort().calculateTime();
    }

    @Override
    public void sort(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCount = new int[10];   //记录每个桶存有多少个数据

        int maxNumber = Arrays.stream(arr).max().getAsInt();//得到数组中最大的数
        int maxLength = (maxNumber + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //(针对每个元素的对应位进行排序处理)， 第一次是个位，第二次是十位，第三次是百位..
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
                bucketElementCount[digitOfElement]++;
            }

            int index = 0;
            for (int j = 0; j < bucket.length; j++) {
                //如果桶中，有数据，我们才放入到原数组
                if (bucketElementCount[j] != 0) {
                    //遍历每一桶，并将桶中是数据，放入到原数组
                    for (int k = 0; k < bucketElementCount[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                }
                bucketElementCount[j] = 0;  //清空通中的数据
            }
        }
    }
}
