package SchoolManagement.System;

// keeping track of students.

import javax.xml.bind.annotation.XmlType;

public class Student {
    private int id;
    private String name;
    private int grade;
    private String className;
    private int fee;
    private int paidFee;

    public static int DEFAULT_FEE = (int) 12e6;

    public Student() {
    }

    public Student(int id, String name, int grade, String className) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.className = className;
        this.fee = DEFAULT_FEE;
        this.paidFee = 0;
    }

    public Student(int id, String name, int grade, String className, int paidFee) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.className = className;
        this.fee = DEFAULT_FEE;
        this.paidFee = paidFee;
    }

    public Student(int id, String name, int grade, String className, int fee, int paidFee) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.className = className;
        this.fee = fee;
        this.paidFee = paidFee;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(int paidFee) {
        this.paidFee = paidFee;
    }

    public void addPaidFee(int fee) {
        paidFee += fee;
    }

    public int getFee() {
        return fee;
    }

}
