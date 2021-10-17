package com.example.models;

public class Student {

    private int id;

    private String name;

    private int age;

    private int high;

    public Student(int id, String name, int age, int high) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.high = high;
    }

    public Student(String name, int age, int high) {
        this.name = name;
        this.age = age;
        this.high = high;
    }

    public String toString() {
        return "student(" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", high=" + high +
                ")\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }
}
