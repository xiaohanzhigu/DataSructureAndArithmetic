package com.ch.data_sructure.Stack;

import com.ch.data_sructure.linklist.List;
import com.ch.data_sructure.linklist.DoubleLinkList;

public class Stack<T> {
    private List<T> stack;  //存储数据
    private int top = -1;   //栈顶

    public Stack() {
        stack = new DoubleLinkList<T>();
    }

    public void push(T value) {
        stack.add(value);
        top++;
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        T value = stack.get(top);
        stack.remove(top);
        top--;
        return value;
    }

    public List<T> getAll() {
        List<T> list = new DoubleLinkList<T>();
        if (stack.isEmpty()) {
            return list;
        }
        for (int i = top; i >= 0; i--) {
            T value = stack.get(i);
            list.add(value);
        }
        return list;
    }

    /**
     * 查看堆栈顶部的对象，而不将其从堆栈中移除。
     * @return
     */
    public T peek() {
        if (stack.isEmpty()) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        T value = stack.get(top);
        return value;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }
}
