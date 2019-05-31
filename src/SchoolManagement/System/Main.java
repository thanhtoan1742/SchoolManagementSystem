package SchoolManagement.System;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CLI cli = new CLI();
        try {
            cli.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
