package com.ch.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestUtil {
    private static Random random;

    /**
     * 返回测试用例
     */
    public static List<Person> getPersonList() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("John", "男", 20));
        list.add(new Person("smith", "男", 30));
        list.add(new Person("李华", "男", 19));
        list.add(new Person("王辉", "男", 42));
        return list;
    }

    /**
     * 随机打乱1 - 10
     * @return
     */
    public static int[] randomNumArr() {
        ArrayList<Integer> c = new ArrayList<>();
        int max = 10;
        for (int i = 1; i <= max; i++) {
            c.add(i);
        }
        Collections.shuffle(c);
        return c.stream().mapToInt(x -> x).toArray();
    }

    /**
     * 随机数
     * @return 返回一个不大于100的数
     */
    public static int randomNum() {
       return randomNum(100);
    }

    /**
     * 随机数
     * @param scope 随机数的最大范围
     * @return 返回一个随机数
     */
    public static int randomNum(int scope){
        if (random == null) {
            random = new Random();
        }
        return random.nextInt(scope);
    }

    /**
     * 随机数组
     * @param num 数组的长度
     * @param scope 数组元素的最大范围
     * @return 随机产生一个int型数组
     */
    public static int[] randomIntArray(int num, int scope) {
        int[] array = new int[num];

        if (random == null) {
            random = new Random();
        }

        for (int i = 0; i < num; i++) {
            array[i] = randomNum(scope);
        }

        return array;
    }


    /**
     * 随机一个最大范围为100的数组
     * @param num 数组元素的长度
     * @return 随机产生一个int型数组
     */
    public static int[] randomIntArray(int num) {
        return randomIntArray(num, 100);
    }
}
