package SchoolManagement.System;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {
    private static final String HEADER = "SMS>";
    private static final String CMD_QUIT = "exit";

    public static void start() throws IOException {
        String cmd = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!cmd.equals(CMD_QUIT)) {
            System.out.print(HEADER);

            cmd = reader.readLine();
            String[] cmdArr = cmd.split(" ");
            //if (cmdArr[0].equals())
        }
    }
}
