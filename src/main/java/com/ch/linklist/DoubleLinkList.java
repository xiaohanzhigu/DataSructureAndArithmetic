package com.ch.linklist;

import java.util.NoSuchElementException;

public class DoubleLinkList<T> implements List<T> {
    private Node headNode = new Node();//头节点
    private Node<T> first = new Node(); //第一个节点
    private Node<T> last = new Node(); //最后一个节点
    private int size;//当前有多少个节点


    //获取全部数据
    public List<T> getAll() {
        List<T> list = new DoubleLinkList<T>();
        //如果headNode没有下一个节点就返回空集合
        if (headNode.next == null) {
            return list;
        }
        Node temp = headNode.next;

        while (true) {
            //遍历完所有节点后返回
            if (temp == null) {
                return list;
            }

            list.add((T) temp.value);
            temp = temp.next;
        }
    }

    public boolean remove(Object o) {
        if (headNode.next == null || o == null) {
            return false;
        }

        Node x = headNode.next;
        while (x != null) {
            if (o == x.value || o.equals(x.value)) {
                unlink(x);
                return true;
            }
            x = x.next;
        }
        return false;
    }

    /**
     * 根据索引删除集合中的内容
     * @param index 删除节点的索引
     * @return 返回删除节点的内容
     */
    public T remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }


    /**
     * 删除位于列表中的节点
     *
     * @param node 要删除的节点
     * @return 返回删除节点的内容
     */
    private T unlink(Node<T> node) {
        //如果要删除的节点是唯一一个节点和是第一个节点
        if (node == first && node.next == null) {
            headNode.next = null;
            first = last = null;
        } else
            //如果要删除的节点是第一个节点
            if (node == first) {
                headNode.next = node.next;
                first = node.next;
            } else
                //如果要删除的节点是尾节点
                if (node == last) {
                    last = node.pre;
                    node.pre.next = null;
                } else
                //如果是其他节点
                {
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                }
        size--;
        return node.value;
    }



    public T get(int index) {
        checkElementIndex(index);
        return node(index).value;
    }


    /**
     * 修改位于列表中指定位置的元素
     *
     * @param index 修改值的元素的索引
     * @param value 修改元素
     */
    public void set(int index, T value) {
        checkElementIndex(index);
        Node<T> node = node(index);
        node.value = value;
    }

    private void checkElementIndex(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException(index + "");
        }
    }

    /**
     * 返回指定元素索引处的(非空)Node
     *
     * @param index 索引
     * @return node
     */
    public Node<T> node(int index) {
        if (index < (size >> 1)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.pre;
            return x;
        }
    }


    public void add(T value) {
        addNode(new Node<T>(value, null, null));
        size++;
    }

    //找到当前链表的最后节点将最后这个节点的next指向新的节点
    private void addNode(Node node) {
        //如果当前链表为空，就将headNode.next和第一个节点指向这个节点
        if (headNode.next == null) {
            headNode.next = node;
            first = node;
        } else {
            //如果链表不为空，就将最后一个节点的next指向新节点
            last.next = node;
        }
        node.pre = last;
        //将最后一个节点指向新节点
        last = node;
    }

    //返回节点数
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            return -1;
        }
        for (Node<T> x = first; x != null; x = x.next) {
            if (o == x.value || o.equals(x.value)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuffer returnStr = new StringBuffer();

        returnStr.append("[");
        for (int i = 0; i < size; i++) {
            T value = this.get(i);
            if (i == size - 1) {
                returnStr.append(value.toString());
            }else {
                returnStr.append(value.toString() + ",");
            }
        }
        returnStr.append("]");

        return returnStr.toString();
    }

    @Override
    public void clear() {
        Node next;
        for (Node x = first; x != null; ) {
            next = x.next;
            x.value = null;
            x.next = null;
            x.pre = null;
            x = next;
        }
        size = 0;
        first = last = null;
    }



    //    //找到当前链表的最后节点将最后这个节点的next指向新的节点
//    private void addNode(Node node) {
//        //因为head节点不能动，因此我们需要一个辅助遍历 temp
//        Node temp = headNode;
//        //遍历链表，找到最后
//        while(true) {
//            if(temp.next == null){
//                break;
//            }
//            //如果此次循环找到的不是最后一个节点就往后移
//            temp = temp.next;
//        }
//
//        //当退出while循环时，temp就指向了链表的最后
//        //将最后这个节点的next 指向 新的节点
//        temp.next = node;
//    }

    class Node<T> {
        T value;
        Node<T> pre;
        Node<T> next;

        public Node(T value, Node<T> pre, Node<T> next) {
            this.value = value;
        }

        public Node() {
        }
    }

    /**
     * 双指针
     * 查找单链表中的倒数第k个节点
     *
     * @param k 倒数第k个节点
     * @return node
     */
    public Node<T> findLastIndexNode(int k) {
        Node<T> fast = first;
        Node<T> slow = first;
        int i = 0;  //记录当前索引

        while (fast.next != null) {
            ++i;
            if (i >= k) {
                slow = slow.next;
            }
            fast = fast.next;
        }
        return slow;
    }

    public T getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.value;
    }

    public T getLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        return last.value;
    }

}

