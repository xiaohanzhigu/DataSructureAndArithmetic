package com.ch.arithmetic.sort;

import com.ch.common.TestUtil;

import java.util.Arrays;

public abstract class SortTemplate {
    public void calculateTime() {
        Runtime r = Runtime.getRuntime();
        r.gc();

        int dataSize = 10000000;
        int[] arr = TestUtil.randomIntArray(dataSize, dataSize);

        long startTime = System.currentTimeMillis(); //开始时间
        long startMem = r.freeMemory(); // 开始时的剩余内存
        sort(arr);
        long endMem = startMem - r.freeMemory(); // 剩余内存 现在
        long endTime = System.currentTimeMillis(); //结束时间

        long millisecond = endTime - startTime; //毫秒
        long second = millisecond / 1000;  //秒
        long memCount = startMem - endMem;
        long memCountK = memCount / 1024;
        long memCountM = memCountK / 1024;

        String name = getClass().getSimpleName();
        System.out.println(name + " " + dataSize + "条数据用时: " + millisecond + "毫秒, " + second + "秒 内存消耗: " + memCountK + "KB, " + memCountM + "MB");
    }

    public void testSort(int... arr) {
        System.out.println("排序前的数组: " + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后的数组: " + Arrays.toString(arr));
    }

    public void testSort(){
        testSort(TestUtil.randomNumArr());
    }


    public abstract void sort(int[] arr);
}
