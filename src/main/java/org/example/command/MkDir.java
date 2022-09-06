package org.example.command;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class MkDir extends Command implements FindFile {
    public MkDir(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        //Creating a File object
        File file;
        //Check if args empty or not
        if (args == null || args.isEmpty()) {
            System.out.println("Enter the path to create a directory: ");
            Scanner sc = new Scanner(System.in);
            String path = sc.next();
            System.out.println("Enter the name of the desired directory: ");
            String name = sc.next();
            file = new File(path, "/" + name);
        } else {
            file = new File(args.get(0));
        }
        //Creating the directory
        boolean bool = file.mkdir();
        if (bool) {
            return file.getName() + " - directory created successfully";
        } else {
            return  "Sorry couldn’t create specified directory";
        }
    }
}
