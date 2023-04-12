package com.example.sqldemo;

public class CustomerModel {
    private int id;
    private String name;
    private static int age;

    // constructors


    public CustomerModel(int id, String name, int age ) {
        this.id=id;
        this.name=name;
        this.age=age;

    }

    public CustomerModel() {
    }
    //toString is necessary for printing the contents of a class object


    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +

                '}';
    }

    // getters and setters
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

    public static int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }




}
