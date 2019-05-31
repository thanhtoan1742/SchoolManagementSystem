package SchoolManagement.System;

public class Main {
    public static void main(String[] args) {
        School CBL = new School();

        CBL.addTeacher(new Teacher(0, "Trinh Van Son", "Toan", 12500000));
        CBL.addTeacher(new Teacher(1, "Pham Cong Tham", "Ly", 12000000));
        CBL.addTeacher(new Teacher(15, "Do Thai Thanh", "Tin", 1000000));

        CBL.addStudent(new Student(0, "Nguyen Thanh Toan", 12, "TT4"));
        CBL.addStudent(new Student(1, "Nguyen Ngoc Khoi Nuyen", 12, "T4"));

        CBL.searchStudentByName("Nguyen Thanh Toan");
        CBL.searchTeacherByName("Pham Cong Tham");
    }
}
