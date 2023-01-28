package com.ch.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Person {
    private String name;
    private String sex;
    private int age;
}
