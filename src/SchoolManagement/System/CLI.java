package SchoolManagement.System;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CLI {
    private static final String HEADER = "SMS>";
    private static final String CMD_QUIT = "exit";
    private static final String CMD_EXPORT = "export";
    private static final String CMD_IMPORT = "import";
    private static final String CMD_ADD = "add";
    private static final String CMD_PRINT_DATA = "printdata";
    private static final String CMD_SEARCH = "search";
    private static final String CMD_SET_SALARY = "setsalary";
    private static final String CMD_PAY = "pay";
    private static final String CMD_REMOVE = "remove";
    private static final String ARG_TEACHER = "teacher";
    private static final String ARG_STUDENT = "student";
    private static final String DATA_FILE = "data.txt";

    public School CBL = new School();

    private void log(String s) {
        System.out.println(s);
    }

    public void start() throws IOException {
        String cmd = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print(HEADER);
            cmd = reader.readLine();
            String[] cmdArr = cmd.split(" ");
            if (cmdArr.length == 0) continue;

            if (cmdArr[0].equals(CMD_QUIT)) break;

            if (cmdArr[0].equals(CMD_ADD))
                if (cmdArr.length == 1) {
                    logAddStudentHelp();
                    logAddTeacherHelp();
                }
                else if (cmdArr[1].equals(ARG_STUDENT)) addStudent(cmdArr);
                else if (cmdArr[1].equals(ARG_TEACHER)) addTeacher(cmdArr);

            if (cmdArr[0].equals(CMD_SEARCH))
                if (cmdArr.length == 1) {

                logSearchStudentHelp();
                logSearchTeacherHelp();
                }
                else if (cmdArr[1].equals(ARG_STUDENT)) searchStudent(cmdArr);
                else if (cmdArr[1].equals(ARG_TEACHER)) searchTeacher(cmdArr);

            if (cmdArr.length == 1) {
            }

            if (cmdArr[0].equals(CMD_REMOVE))
                if (cmdArr.length == 1) {
                    logRemoveStudentHelp();
                    logRemoveTeacherHelp();
                }
                else if (cmdArr[1].equals(ARG_STUDENT)) removeStudent(cmdArr);
                else if (cmdArr[1].equals(ARG_TEACHER)) removeTeacher(cmdArr);

            if (cmdArr[0].equals(CMD_EXPORT)) exportData();
            if (cmdArr[0].equals(CMD_IMPORT)) importData();
            if (cmdArr[0].equals(CMD_PRINT_DATA)) printData();
            if (cmdArr[0].equals(CMD_PAY)) payStudent(cmdArr);
            if (cmdArr[0].equals(CMD_SET_SALARY)) setTeacherSalary(cmdArr);
        }
    }

    /**
     * Parse and add new student to database.
     */
    private void addStudent(String[] cmdArr) {
        if (cmdArr.length < 6) {
            logAddStudentHelp();
            return;
        }

        int id = Integer.parseInt(cmdArr[2]);
        String name = cmdArr[3];
        int grade = Integer.parseInt(cmdArr[4]);
        String className = cmdArr[5];

        if (cmdArr.length == 6) CBL.addStudent(new Student(id, name, grade, className));
        if (cmdArr.length == 7) {
            int paidFee = Integer.parseInt(cmdArr[6]);
            CBL.addStudent(new Student(id, name, grade, className, paidFee));
        }
        if (cmdArr.length == 8) {
            int fee = Integer.parseInt(cmdArr[6]);
            int paidFee = Integer.parseInt(cmdArr[7]);
            CBL.addStudent(new Student(id, name, grade, className, fee, paidFee));
        }
        System.out.println("Student added");
    }

    /**
     * Parse and add new teacher to database.
     */
    private void addTeacher(String[] cmdArr) {
        if (cmdArr.length < 6) {
            logAddTeacherHelp();
            return;
        }

        int id = Integer.parseInt(cmdArr[2]);
        String name = cmdArr[3];
        String profession = cmdArr[4];
        int salary = Integer.parseInt(cmdArr[5]);

        CBL.addTeacher(new Teacher(id, name, profession, salary));
        System.out.println("Teacher added");
    }

    /**
     * Search student by name then print out all information about
     * all students have the same name.
     */
    private void searchStudent(String[] cmdArr) {
        if (cmdArr.length < 3) {
            logSearchStudentHelp();
            return;
        }
        String name = cmdArr[2];

        ArrayList<Student> students = CBL.getStudents();
        for (int i = 0; i < students.size(); i++)
            if (name.equals(students.get(i).getName()))
                System.out.printf("%-5d%-25s%-10d%-10s%-15d%-15d%n", students.get(i).getId(), students.get(i).getName(),
                        students.get(i).getGrade(), students.get(i).getClassName(),
                        students.get(i).getFee(), students.get(i).getPaidFee());
    }

    /**
     * Search teacher by name then print out all information about
     * all students have the same name.
     */
    private void searchTeacher(String[] cmdArr) {
        if (cmdArr.length < 3) {
            logSearchTeacherHelp();
            return;
        }
        String name = cmdArr[2];

        ArrayList<Teacher> teachers = CBL.getTeachers();
        for (int i = 0; i < teachers.size(); i++)
            if (name.equals(teachers.get(i).getName()))
                System.out.printf("%-5d%-25s%-10s%-15d%n", teachers.get(i).getId(),
                        teachers.get(i).getName(), teachers.get(i).getProfession(),
                        teachers.get(i).getSalary());
    }

    /**
     * Remove student with id from database.
     */
    private void removeStudent(String[] cmdArr) {
        if (cmdArr.length < 3) {
            logRemoveStudentHelp();
            return;
        }

        int id = Integer.parseInt(cmdArr[2]);
        CBL.removeStudent(id);
        log("Student with id " + Integer.toString(id) +" has been removed");
    }

    /**
     * Remove teacher with id from database.
     */
    private void removeTeacher(String[] cmdArr) {
        if (cmdArr.length < 3) {
            logRemoveTeacherHelp();
            return;
        }

        int id = Integer.parseInt(cmdArr[2]);
        CBL.removeTeacher(id);
        log("Teacher with id " + Integer.toString(id) +" has been removed");
    }

    /**
     * Get paid money of student to database.
     */
    private void payStudent(String[] cmdArr) {
        if (cmdArr.length < 3) {
            logPayStudentHelp();
            return;
        }

        int id = Integer.parseInt(cmdArr[1]);
        int fee = Integer.parseInt(cmdArr[2]);

        CBL.addStudentPaidFee(id, fee);
        log("Student with id " + Integer.toString(id) + " has paid " + Integer.toString(fee));
    }

    /**
     * Change teacher's salary.
     */
    private void setTeacherSalary(String[] cmdArr) {
        if (cmdArr.length < 3) {
            logSetTeacherSalaryHelp();
            return;
        }

        int id = Integer.parseInt(cmdArr[1]);
        int newSalary = Integer.parseInt(cmdArr[2]);

        CBL.setTeacherSalary(id, newSalary);
        log("Teacher with id " + Integer.toString(id) + " 's salary has been set to " + Integer.toString(newSalary));
    }

    /**
     * Data is exported to DATA_FILE.
     * This data also serves as database.
     */
    private void exportData() {
        try {
            PrintWriter fo = new PrintWriter(DATA_FILE);

            /**
             * Print teacher's data first.
             * First line is the number of teachers.
             * Second line and so on are teachers.
             * Format: ID NAME PROFESSION SALARY.
             */
            ArrayList<Teacher> teachers = CBL.getTeachers();
            fo.printf("%d%n", teachers.size());
            for (int i = 0; i < teachers.size(); i++)
                fo.printf("%-5d%-25s%-10s%-15d%n", teachers.get(i).getId(),
                        teachers.get(i).getName(), teachers.get(i).getProfession(),
                        teachers.get(i).getSalary());

            /**
             * Next is student's data.
             * First line is the number of students.
             * second line and so on are students.
             * FORMAT: ID NAME GRADE CLASSNAME FEE PAIDFEE.
             */
            ArrayList<Student> students = CBL.getStudents();
            fo.printf("%d%n", students.size());
            for (int i = 0; i < students.size(); i++)
                fo.printf("%-5d%-25s%-10d%-10s%-15d%-15d%n", students.get(i).getId(), students.get(i).getName(),
                        students.get(i).getGrade(), students.get(i).getClassName(),
                        students.get(i).getFee(), students.get(i).getPaidFee());

            fo.close();
        }
        catch (IOException e) {
            log("File Not Found");
        }

        log("Data exported to " + DATA_FILE);
    }

    /**
     * Import data from DATA_FILE.
     */
    private void importData() throws IOException {
        File file = new File(DATA_FILE);
        Scanner scanner = new Scanner(file);

        /**
         * Read teacher's data first.
         */
        int n = scanner.nextInt();
        while (n > 0) {
            n--;
            int id = scanner.nextInt();
            String name = scanner.next();
            String profession = scanner.next();
            int salary = scanner.nextInt();

            CBL.getTeachers().add(new Teacher(id, name, profession, salary));
        }

        /**
         * Read student's data.
         */
        n = scanner.nextInt();
        while (n > 0) {
            n--;
            int id = scanner.nextInt();
            String name = scanner.next();
            int grade = scanner.nextInt();
            String className = scanner.next();
            int fee = scanner.nextInt();
            int paidFee = scanner.nextInt();

            CBL.getStudents().add(new Student(id, name, grade, className, fee, paidFee));
        }

        log("Data Imported from " + DATA_FILE);
    }

    /**
     * Print all data to the console.
     */
    private void printData() {
        ArrayList<Teacher> teachers = CBL.getTeachers();
        System.out.printf("%d%n", teachers.size());
        for (int i = 0; i < teachers.size(); i++)
            System.out.printf("%-5d%-25s%-10s%-15d%n", teachers.get(i).getId(),
                    teachers.get(i).getName(), teachers.get(i).getProfession(),
                    teachers.get(i).getSalary());

        ArrayList<Student> students = CBL.getStudents();
        System.out.printf("%d%n", students.size());
        for (int i = 0; i < students.size(); i++)
            System.out.printf("%-5d%-25s%-10d%-10s%-15d%-15d%n", students.get(i).getId(), students.get(i).getName(),
                    students.get(i).getGrade(), students.get(i).getClassName(),
                    students.get(i).getFee(), students.get(i).getPaidFee());
    }

    private void logAddStudentHelp() {
        log("Syntax: add student <id> <name without spaces> <grade> <className>");
        log("Syntax: add student <id> <name without spaces> <grade> <className> <paidFee>");
        log("Syntax: add student <id> <name without spaces> <grade> <className> <fee> <paidFee>");
    }

    private void logAddTeacherHelp() {
        log("Syntax: add teacher <id> <name without spaces> <profession> <salary>");
    }

    private void logSearchStudentHelp() {
        log("Syntax: search student <name without spaces>");
    }

    private void logSearchTeacherHelp() {
        log("Syntax: search teacher <name without spaces>");
    }

    private void logRemoveStudentHelp() {
        log("Syntax: remove student <id>");
    }

    private void logRemoveTeacherHelp() {
        log("Syntax: remove teacher <id>");
    }

    private void logPayStudentHelp() {
        log("Syntax: pay <student id> <money to pay>");
    }

    private void logSetTeacherSalaryHelp() {
        log("Syntax: setsalary <teacher id> <new salary>");
    }

    public static void main(String[] args) {
        CLI cli = new CLI();
        try {
            cli.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
