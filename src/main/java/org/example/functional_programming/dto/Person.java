package org.example.functional_programming.dto;

import java.util.List;

public class Person {
    private String name;
    private int age;
    private double weight;
    private Gender gender;
    private List<Phone> phones;

    public Person(String name, int age, double weight, Gender gender, List<Phone> phones) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.phones = phones;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", gender=" + gender +
                ", phones=" + phones +
                '}';
    }
}
