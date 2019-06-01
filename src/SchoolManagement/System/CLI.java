package SchoolManagement.System;

import java.io.*;
import java.util.ArrayList;

public class CLI {
    private static final String HEADER = "SMS>";
    private static final String CMD_QUIT = "exit";
    private static final String CMD_EXPORT = "export";
    private static final String CMD_ADD = "add";
    private static final String ARG_TEACHER = "teacher";
    private static final String ARG_STUDENT = "student";
    private static final String OUTPUT_FILE = "data.txt";

    public School CBL = new School();

    private void log(String s) {
        System.out.println(s);
    }

    public void start() throws IOException {
        String cmd = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!cmd.equals(CMD_QUIT)) {
            System.out.print(HEADER);
            cmd = reader.readLine();
            String[] cmdArr = cmd.split(" ");

            if (cmdArr.length == 0) continue;

            if (cmdArr[0].equals(CMD_ADD)) {
                if (cmdArr.length == 1) {
                    logAddStudentHelp();
                    logAddTeacherHelp();
                } else if (cmdArr[1].equals(ARG_STUDENT)) {
                    if (cmdArr.length < 6)
                        logAddStudentHelp();
                    else {
                        int id = Integer.parseInt(cmdArr[2]);
                        String name = cmdArr[3];
                        int grade = Integer.parseInt(cmdArr[4]);
                        String className = cmdArr[5];

                        if (cmdArr.length == 6) CBL.addStudent(new Student(id, name, grade, className));
                        else if (cmdArr.length == 7) {
                            int paidFee = Integer.parseInt(cmdArr[6]);
                            CBL.addStudent(new Student(id, name, grade, className, paidFee));
                        } else {
                            int fee = Integer.parseInt(cmdArr[6]);
                            int paidFee = Integer.parseInt(cmdArr[7]);
                            CBL.addStudent(new Student(id, name, grade, className, fee, paidFee));

                        }
                    }
                } else if (cmdArr[1].equals(ARG_TEACHER)) {
                    if (cmdArr.length < 6)
                        logAddTeacherHelp();
                    else {
                        int id = Integer.parseInt(cmdArr[2]);
                        String name = cmdArr[3];
                        String profession = cmdArr[4];
                        int salary = Integer.parseInt(cmdArr[5]);

                        CBL.addTeacher(new Teacher(id, name, profession, salary));
                    }
                }
            }
            else if (cmdArr[0].equals(CMD_EXPORT))
                exportData();
        }
    }

    private void exportData() {
        try {
            PrintWriter fo = new PrintWriter(OUTPUT_FILE);

            ArrayList<Teacher> teachers = CBL.getTeachers();
            fo.printf("%d%n", teachers.size());
            for (int i = 0; i < teachers.size(); i++)
                fo.printf("%-5d%-25s%-10s%-15d%n", teachers.get(i).getId(),
                        teachers.get(i).getName(), teachers.get(i).getProfession(),
                        teachers.get(i).getSalary());

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

        log("Data exported to data.txt");
    }

    private void importData() {
        
    }

    private void logAddStudentHelp() {
        log("Syntax: add student <id> <name without spaces> <grade> <className>");
        log("Syntax: add student <id> <name without spaces> <grade> <className> <paidFee>");
        log("Syntax: add student <id> <name without spaces> <grade> <className> <fee> <paidFee>");
    }

    private void logAddTeacherHelp() {
        log("Syntax: add teacher <id> <name without spaces> <profession> <salary>");
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
