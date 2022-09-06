package org.example.command;

import lombok.SneakyThrows;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class MkFile extends Command implements FindFile {
    public MkFile(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        //Creating a File object
        File file;
        //Creating a File with args null
        if (args == null || args.isEmpty()) {
            System.out.println("Enter the path to create a file: ");
            Scanner sc = new Scanner(System.in);
            String path = sc.next();
            System.out.println("Enter the name of the desired file: ");
            String name = sc.next();
            file = new File(path, "/" + name);
        } else { //Creating a File with not null args
            file = new File(args.get(0));
        }
        //Creating the file
        boolean bool = file.createNewFile();
        if (bool) {
            return file.getName() + " - file created successfully";
        } else {
            return  "Sorry couldn’t create specified file";
        }
    }
}
