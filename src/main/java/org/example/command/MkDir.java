package org.example.command;

import java.io.File;
import java.util.List;

public class MkDir extends Command implements FindFile {
    public MkDir(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()){
            return "Enter the args pls.";
        }
        String path = args.get(0);
        //Creating a File object
        File file = new File(path);
        findFile(args, context);
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
