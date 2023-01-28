package com.ch.search;

public class BinarySearch extends SearchTemplate {
    public static void main(String[] args) {
        int[] ints = new int[]{1,8, 10, 89, 1000, 1234};
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.search(ints, 10));
    }

    @Override
    public int search(int[] arr, int target) {
        return binarySearch(arr, 0, arr.length - 1, target);
    }

    public int binarySearch(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        if (arr[mid] < target) {
            return binarySearch(arr, mid + 1, right, target);
        } else if (arr[mid] > target){
            return binarySearch(arr, left, mid - 1, target);
        } else {
            return mid;
        }

    }
}
