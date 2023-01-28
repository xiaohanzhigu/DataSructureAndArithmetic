package com.ch.data_sructure.hash;

import java.util.ArrayList;
import java.util.List;

public class HashTable<V> {
    private LinkList<V>[] linkLists;
    private int size;

    private int hash(Object o) {
        return (o == null) ? 0 : o.hashCode() % size;
    }

    public void add(V value) {
        int hash = hash(value);
        if (linkLists[hash] == null) {
            linkLists[hash] = new LinkList<>();
        }
        linkLists[hash].add(hash, value);
    }

    public List<V> asList() {
        List<V> list = new ArrayList<>();
        LinkList<V> linkList;
        for (int i = 0; i < size; i++) {
            linkList = linkLists[i];
            if (linkList == null) {
                continue;
            }
            list.addAll(linkList.asList());
        }
        return list;
    }


    public HashTable() {
        this.size = 8;
        linkLists = new LinkList[size];
    }

    public HashTable(int size) {
        this.size = size;
        linkLists = new LinkList[size];
    }


    class LinkList<V> {
        private Node<V> headNode;
        private Node<V> last;
        private int size;

        public void add(int hash, V value) {
            addNode(new Node<V>(hash, value, null));
            size++;
        }

        public void addNode(Node<V> node) {
            if (headNode == null) {
                headNode = node;
            } else {
                last.next = node;
            }
            last = node;
        }

        public List<V> asList() {
            if (headNode == null) {
                return null;
            }
            List<V> list = new ArrayList<>();
            Node<V> n = headNode;
            while (n != null) {
                list.add(n.getValue());
                n = n.next;
            }
            return list;
        }
    }

    class Node<V> {
        int hash;
        V value;
        Node<V> next;

        public Node(int hash, V value, Node<V> next) {
            this.hash = hash;
            this.value = value;
            this.next = next;
        }

        public Node() {
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
