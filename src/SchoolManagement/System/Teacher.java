package SchoolManagement.System;

public class Teacher {
    private int id;
    private String name;
    private String profession;
    private int salary;

    public Teacher(int id, String name, String profession, int salary) {
        this.id = id;
        this.name = name;
        this.profession = profession;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
