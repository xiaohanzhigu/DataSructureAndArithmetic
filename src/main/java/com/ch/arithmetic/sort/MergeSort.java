package com.ch.arithmetic.sort;

/**
 * 归并排序
 */
public class MergeSort extends SortTemplate{
    public static void main(String[] args) {
        new MergeSort().calculateTime();
    }

    @Override
    public void sort(int[] arr) {
        int[] temp = new int[arr.length];
        divide(arr, 0, arr.length - 1, temp);
    }

    public void divide(int[]arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            divide(arr, left, mid, temp);
            divide(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    public void merge(int[]arr, int left, int mid, int right, int[] temp) {
        int i = left;  //左部分开始索引
        int j = mid + 1;  //右部分开始索引
        int t = 0;  //临时数组索引

        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }

        //把有剩余数据的一边的数据填充到temp数组
        while (i <= mid) {  //左边
            temp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right) {    //右边
            temp[t] = arr[j];
            j++;
            t++;
        }

        //将temp数组数据填充到arr数组
        //注意: 并不是每次都拷贝全部数据
        //第一次合并 tempLeft = 0 , right = 1 //  tempLeft = 2  right = 3 // tL=0 ri=3
        //最后一次 tempLeft = 0  right = 7
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }
}
