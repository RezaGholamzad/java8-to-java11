package com.oracle;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        ProcessHandle currentProcess = ProcessHandle.current();     // Current processhandle
        System.out.println("Process Id: "+currentProcess.pid());    // Process id
        System.out.println("Direct children: "+ currentProcess.children()); // Direct children of the process
        System.out.println("Class name: "+currentProcess.getClass());       // Class name
        System.out.println("All processes: "+ProcessHandle.allProcesses()); // All current processes
        System.out.println("Process info: "+currentProcess.info());         // Process info
        System.out.println("Is process alive: "+currentProcess.isAlive());
        System.out.println("Process's parent: "+currentProcess.parent());  // Parent of the process

        System.out.println("**************************");

        ProcessBuilder pb = new ProcessBuilder("notepad.exe");
        String np = "Not Present";
        Process p = pb.start();
        ProcessHandle.Info info = p.info();
        System.out.printf("Process ID : %s%n", p.pid());
        System.out.printf("Command name : %s%n", info.command().orElse(np));
        System.out.printf("Command line : %s%n", info.commandLine().orElse(np));
        System.out.printf("Start time: %s%n",
                info.startInstant().map(i -> i.atZone(ZoneId.systemDefault())
                        .toLocalDateTime().toString()).orElse(np));
        System.out.printf("Arguments : %s%n",
                info.arguments().map(a -> String.join(" ", a)).orElse(np));
        System.out.printf("User : %s%n", info.user().orElse(np));

        System.out.println("**************************");

        Stream<ProcessHandle> processHandleStream = ProcessHandle.allProcesses(); // all process
//        processHandleStream.forEach(Main::getDetails);

    }

    private static void getDetails(ProcessHandle processHandle){
        ProcessHandle.Info info = processHandle.info();

        System.out.println("Pid: " + processHandle.pid());
        System.out.println("User: " + info.user().orElse(""));
        System.out.println("Command: " + info.command().orElse(""));
        System.out.println("CPUDuration: " + info.totalCpuDuration().orElse(Duration.ofMillis(0)).toMillis());
        System.out.println("StartInstant: " + info.startInstant().orElse(Instant.now()).toString() + "\n");
    }
}
