package SchoolManagement.System;

// keeping track of students.

public class Student {
    private int id;
    private String name;
    private int grade;
    private String className;
    private int fee;
    private int paidFee;

    public static final int Default_Fee = (int) 12e6;

    public Student() {
    }

    public Student(int id, String name, int grade, String className) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.className = className;
        this.fee = Default_Fee;
        this.paidFee = 0;
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
