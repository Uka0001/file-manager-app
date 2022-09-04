package org.example.command;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class MkDirPath extends Command {
    public MkDirPath(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        System.out.println("Enter the path to create a directory: ");
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        if (args == null || args.isEmpty()) {
            System.out.println("Enter the name of the desired a directory: ");
            path = path + File.separator + sc.next();
        } else {
            path = path + File.separator + args.toString();
        }
        //Creating a File object
        File file = new File(path);
        //Creating the directory
        boolean bool = file.mkdir();
        if (bool) {
            System.out.println("Directory created successfully");
        } else {
            System.out.println("Sorry couldn’t create specified directory");
        }
        return file.getName();
    }
}
