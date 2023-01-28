package com.ch.data_sructure.hash;

import com.ch.common.Person;

import java.util.List;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable<Person> table = new HashTable<>();
        Person p1 = new Person("a", "x", 18);
        Person p2 = new Person("b", "x", 18);
        Person p3 = new Person("c", "x", 18);
        Person p4 = new Person("d", "x", 18);
        //table.add(p1);
        //table.add(p2);
        //table.add(p3);
        //table.add(p4);
        List<Person> people = table.asList();
        System.out.println(people);
    }
}
