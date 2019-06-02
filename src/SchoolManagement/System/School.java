package SchoolManagement.System;

import java.util.ArrayList;

public class School {
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private int totalMoneyEarned;
    private int totalMoneyPaid;

    public School() {
        teachers = new ArrayList<Teacher>();
        students = new ArrayList<Student>();
        totalMoneyEarned = 0;
        totalMoneyPaid = 0;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher newTeacher) {
        teachers.add(newTeacher);
        totalMoneyPaid += newTeacher.getSalary();
    }

    public void removeTeacher(int id) {
        for (int i = 0; i < teachers.size(); i++)
            if (teachers.get(i).getId() == id) {
                totalMoneyPaid -= teachers.get(i).getSalary();
                teachers.remove(i);
                break;
            }
    }

    public void setTeacherSalary(int id, int newSalary) {
        for (int i = 0; i < teachers.size(); i++)
            if (teachers.get(i).getId() == id)  {
                totalMoneyPaid += newSalary - teachers.get(i).getSalary();
                teachers.get(i).setSalary(newSalary);
            }
    }

    public void searchTeacherByName(String name) {
        System.out.printf("%-10s%-30s%-15s%n", "ID", "NAME", "SALARY");
        for (int i = 0; i < teachers.size(); i++)
            if (teachers.get(i).getName() == name) {
                System.out.printf("%-10d%-30s%-15d%n", teachers.get(i).getId(), teachers.get(i).getName(), teachers.get(i).getSalary());
            }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student newStudent) {
        students.add(newStudent);
        totalMoneyEarned += newStudent.getPaidFee();
    }

    public void removeStudent(int id) {
        for (int i = 0; i < students.size(); i++)
            if (students.get(i).getId() == id) {
                totalMoneyEarned -= students.get(i).getPaidFee();
                students.remove(i);
                break;
            }
    }

    public void addStudentPaidFee(int id, int fee) {
        for (int i = 0; i < students.size(); i++)
            if (students.get(i).getId() == id) {
                totalMoneyEarned += fee;
                students.get(i).addPaidFee(fee);
                break;
            }
    }

    public void searchStudentByName(String name) {
        System.out.printf("%-10s%-30s%-15s%-15s%n", "ID", "NAME", "Total Fee", "Paid Fee");
        for (int i = 0; i < students.size(); i++)
            if (students.get(i).getName() == name) {
                System.out.printf("%-10d%-30s%-15d%-15d%n", students.get(i).getId(), students.get(i).getName(), students.get(i).getFee(), students.get(i).getPaidFee());
            }
    }

    public int getTotalMoneyEarned(){
        return totalMoneyEarned;
    }

    public int getTotalMoneyPaid() {
        return totalMoneyPaid;
    }

    public int getTotalMoneyLeft() {
        return totalMoneyEarned - totalMoneyPaid;
    }
}
