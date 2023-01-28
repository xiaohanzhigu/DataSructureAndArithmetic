package com.ch.linklist;

import com.ch.common.Person;

public class DoubleLinkListDemo {
    public static void main(String[] args) {
        DoubleLinkList<Person> linkList = new DoubleLinkList<>();
        linkList.add(new Person("John", "男", 20));
        linkList.add(new Person("smith", "男", 30));
        Person person = new Person("李华", "男", 19);
        linkList.add(person);
        linkList.add(new Person("王辉", "男", 42));
        System.out.println(linkList.getAll());

        System.out.println(linkList.indexOf(person));
        linkList.clear();

    }
}



