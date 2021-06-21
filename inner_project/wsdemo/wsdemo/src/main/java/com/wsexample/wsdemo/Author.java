package com.wsexample.wsdemo;

public class Author {
    private String name;
    private int age;

    public Author(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        result.append(" Name: ");
        result.append(name);
        result.append(" Age: ");
        result.append(age);
        return result.toString();
    }
}
