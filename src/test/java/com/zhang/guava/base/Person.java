package com.zhang.guava.base;

/**
 * @author <p>yuyang.zhang<p>
 * @date 2018-12-13 15:02
 * @since 1.0
 */
public class Person {

    private String name;

    private String address;

    private int age;

    private String sex;

    public Person(String name, String address, int age, String sex) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
