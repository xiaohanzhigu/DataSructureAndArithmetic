package com.ch.arithmetic.sort;

/**
 * 堆排序
 * 堆排序的基本思想是：
 * 将待排序序列构造成一个大顶堆
 * 此时，整个序列的最大值就是堆顶的根节点。
 * 将其与末尾元素进行交换，此时末尾就为最大值。
 * 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
 * 大顶堆特点：arr[i] >= arr[2*i+1] && arr[i] >= arr[2*i+2]  // i 对应第几个节点，i从0开始编号
 * 小顶堆：arr[i] <= arr[2*i+1] && arr[i] <= arr[2*i+2] // i 对应第几个节点，i从0开始编号
 * 一般升序采用大顶堆，降序采用小顶堆
 */
public class HeapSort extends SortTemplate {
    public static void main(String[] args) {
        new HeapSort().testSort(4, 6, 8, 5, 9);
        //new HeapSort().calculateTime();
    }

    @Override
    public void sort(int[] arr) {
        //1).将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        /*
         * 2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
         * 3).重新调整结构，使其满足堆定义，然后继续交换堆顶元素与反复执行调整+当前末尾元素，交换步骤，直到整个序列有序。
         */
        for (int j = arr.length - 1; j > 0; j--) {
            arr[j] = arr[j] ^ arr[0];
            arr[0] = arr[j] ^ arr[0];
            arr[j] = arr[j] ^ arr[0];
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 功能： 完成 将 以 i 对应的非叶子结点的树调整成大顶堆
     * 举例  int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用  adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子结点在数组中索引
     * @param length 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    private void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { //说明左子结点的值小于右子结点的值
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k; //i 指向 k,继续循环比较
            } else {
                break;
            }
            arr[i] = temp;
        }
    }
}
