package com.ch.data_sructure.queue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * new完异常后要抛出去！！！！！！！！！！！！！！
 * 对数组模拟队列的优化，充分利用数组.   因此将数组看做是一个环形的。(通过取模的方式来实现即可)
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue<Integer> queue = new CircleArrayQueue<>(Integer.class, 4);//说明设置4, 其队列的有效数据最大是3
        queue.put(1);
        queue.put(2);
        queue.put(3);
        System.out.println(queue.getAll());

        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.getAll());

        queue.put(4);
        queue.put(5);
        try {
            queue.put(5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(queue.getAll());
    }
}

class CircleArrayQueue<T>{
    private int maxSize; // 表示数组的最大容量
    //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    //front 的初始值 = 0
    private int front;
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    //rear 的初始值 = 0
    private int rear; // 队列尾
    private T[] arr; // 该数据用于存放数据, 模拟队列

    public CircleArrayQueue(Class<T> componentType, int size){
        maxSize = size;
        arr = (T[]) Array.newInstance(componentType, maxSize);
    }

    //添加
    public void put(T date) {
        //判断是否已满
        if (isFull()){
            throw new RuntimeException("队列已满");
        }

        //以为rear指向的是最后一个元素的后一个元素
        arr[rear] = date;
        //将rear往后移动
        rear = (rear + 1) % maxSize;
    }

    //取出
    public T get() {
        //判断是否为空
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }

        // front是指向队列的第一个元素
        // 1. 先把 front 对应的值保留到一个临时变量
        // 2. 将 front 后移, 考虑取模
        // 3. 将临时保存的变量返回
        T value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //取出所有数据
    public List<T> getAll() {
        //判断是否为空
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }

        List<T> list = new ArrayList<>();
        //从front开始遍历，遍历n个有效元素
        for (int i = front; i < front + size(); i++) {
            list.add(arr[i % maxSize]);
        }

        return list;
    }

    //返回头元素不是取出头元素
    public T headElement() {
        //判断是否为空
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }

        return arr[front];
    }

    //判断当前队列有几个有效元素
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }


    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 判断队列是否满
    public boolean isFull() {
        return (rear  + 1) % maxSize == front;
    }
}
