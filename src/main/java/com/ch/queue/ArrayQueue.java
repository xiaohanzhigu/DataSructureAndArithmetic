package com.ch.queue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayQueue<T>{
    private int maxSize; //表示队列的最大容量
    private int front;  //队列头
    private int rear;   //队列尾
    private T[] arr;   //用于存储数据

    public ArrayQueue(Class<T> componentType, int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = (T[]) Array.newInstance(componentType, maxSize);
        front = -1; //指向队列头部，front是指向队列头的前一个位置
        rear = -1;
    }

    //添加
    public void addQueue(T date){
        //判断是否已满
        if(isFull()){
            throw new RuntimeException("队列已满");
        }

        arr[++rear] = date;
    }

    //取出
    public T getQueue(){
        //判断是否为空
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }

        return arr[++front];
    }

    //取出所有数据
    public List<T> getAll() {
        //判断是否为空
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }

        List<T> list = new ArrayList<>();
        for (int i = front + 1; i <= rear; i++) {
            list.add(arr[i]);
        }

        return list;
    }

    public T headQueue(){
        return arr[front + 1];
    }

    //判断是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //判断是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }
}
