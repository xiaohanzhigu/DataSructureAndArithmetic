package com.ch.linklist;

public interface List<T> {
    /**
     * 添加
     * @param value 添加的数据
     */
    void add(T value);

    /**
     * 返回位于列表中指定位置的元素
     * @param index-返回返回值的元素的索引
     * @return 在列表中指定位置的元素
     */
    T get(int index);

    /**
     * 修改位于列表中指定位置的元素
     * @param index 修改值的元素的索引
     * @param value 修改元素
     */
    void set(int index, T value);

    /**
     * 根据索引删除集合中的内容
     * @param index 删除节点的索引
     * @return 返回删除节点的内容
     */
    T remove(int index);

    /**
     * 链表的节点数
     * @return 节点数
     */
    int size();

    /**
     * 判断链表是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 查找元素第一次出现的位置
     * @param o 要查找的元素
     * @return 查找元素在列表中第一次出现的索引，如果列表中不包含该元素，则为-1
     */
    int indexOf(Object o);

    /**
     * 移除列表中的所有元素
     */
    void clear();
}
