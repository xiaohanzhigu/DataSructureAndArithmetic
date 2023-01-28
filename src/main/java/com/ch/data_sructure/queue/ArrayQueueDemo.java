package com.ch.data_sructure.queue;

/**
 * 队列本身是有序列表，若使用数组的结构来存储队列的数据，则队列数组的声明如下图, 其中 maxSize 是该队列的最大容量。
 *
 * 因为队列的输出、输入是分别从前后端来处理，因此需要两个变量 front及 rear分别记录队列前后端的下标，
 * front 会随着数据输出而改变，而 rear则是随着数据输入而改变
 *
 * 当我们将数据存入队列时称为”addQueue”，addQueue 的处理需要有两个步骤：
 * 思路分析 :
 * 1. 将尾指针往后移：rear+1 , 当front == rear 【空】
 * 2. 若尾指针 rear 小于队列的最大下标 maxSize-1，则将数据存入 rear所指的数组元素中，否则无法存入数据。 rear == maxSize - 1[队列满]
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(Integer.class, 5);
        arrayQueue.addQueue(1);
        arrayQueue.addQueue(2);
        arrayQueue.addQueue(3);
        arrayQueue.addQueue(4);
        arrayQueue.addQueue(5);
        arrayQueue.addQueue(5);
        arrayQueue.addQueue(5);
        arrayQueue.addQueue(5);

        System.out.println(arrayQueue.getQueue());
        System.out.println(arrayQueue.getQueue());
        System.out.println(arrayQueue.getQueue());
        System.out.println(arrayQueue.getQueue());
        System.out.println(arrayQueue.getQueue());
        System.out.println(arrayQueue.getAll());

        ArrayQueue<Person> personArrayQueue = new ArrayQueue<>(Person.class, 5);
        personArrayQueue.addQueue(new Person("smith"));
        personArrayQueue.addQueue(new Person("job"));
        personArrayQueue.addQueue(new Person("join"));
        personArrayQueue.addQueue(new Person("j"));
        personArrayQueue.addQueue(new Person("小明"));

        System.out.println(personArrayQueue.headQueue());
        System.out.println(personArrayQueue.getQueue());
        System.out.println(personArrayQueue.getQueue());
        System.out.println(personArrayQueue.getQueue());
        System.out.println(personArrayQueue.getAll());
        System.out.println(personArrayQueue.getQueue());
        System.out.println(personArrayQueue.getQueue());

    }
}



class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
