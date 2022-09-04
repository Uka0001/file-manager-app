package org.example.command;

import java.io.File;
import java.util.List;

public class MkDir extends Command {
    public MkDir(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        String path = args.get(0);
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
