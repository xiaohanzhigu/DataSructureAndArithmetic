package com.ch.sort;

public class QuickSort extends SortTemplate {
    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        r.gc();
        long startMem = r.freeMemory(); // 开始时的剩余内存
        new QuickSort().calculateTime();
        long orz = startMem - r.freeMemory(); // 剩余内存 现在
        System.out.println(startMem + " " + startMem / 1021 /1024);
        System.out.println(orz+ " " + orz / 1021 /1024);

    }

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public void sort(int[] arr, int left, int right) {
        int l = left, r = right, pivot = arr[(right + left) / 2], temp;
        while (l < r) {
            //找一个比中值小的数
            while (arr[l] < pivot) {
                l++;
            }
            //找一个不中值大的数
            while (arr[r] > pivot) {
                r--;
            }

            //如果left>=right说明数组左边是比中值小的数，数组右边是比中值大的数
            if (l >= r) {
                break;
            }

            arr[l] = arr[l] ^ arr[r];
            arr[r] = arr[l] ^ arr[r];
            arr[l] = arr[l] ^ arr[r];

            //如果arr[l]或者arr[r]等于midNum了那么下次循环就不会去找下一个比中值小或者大的数了。第19-26行
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }

        if (left < r) {
            sort(arr, left, r);
        }
        if (right > l) {
            sort(arr, l, right);
        }
    }

}














