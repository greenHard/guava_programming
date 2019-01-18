package com.zhang.guava.cache;

public class Employee {
    private final String name;
    private final String dept;
    private final String empID;
    private final byte[] data = new byte[1024 * 1024];

    public Employee(String name, String dept, String empID) {
        this.name = name;
        this.dept = dept;
        this.empID = empID;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getEmpID() {
        return empID;
    }
}