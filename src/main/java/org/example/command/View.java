package org.example.command;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class View extends Command {
    public View(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        context.getCurrentDirectory().listFiles();
        //Search for [.txt] list files in directory and pick:
        System.out.println("Enter .txt file name that you want to view from the list above");
        File[] files = context.getCurrentDirectory().listFiles();
        // output on console
        System.out.println("Print .txt file name from following list:");
        for (File each : files) {
            System.out.println(each.getName());
        }
        // reading name of file
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        // testing if equals names in folder
        for (File each : files) {
            if (name.toLowerCase().trim().equals(each.getName().toLowerCase().trim())) {
                // print .txt file
                BufferedReader in = new BufferedReader(new FileReader(each));
                String line = in.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = in.readLine();
                }
                in.close();
                return "Task completed, whats next?";
            } else {
                // print error for user if file is not exist
                System.out.println("Invalid input. Please enter a valid name");
            }
        }
        //  close the scanner when finished
        scanner.close();

        return "q"; //exit app
    }
}
