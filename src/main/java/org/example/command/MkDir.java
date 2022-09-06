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
        //Creating a File object
        File file = new File(findFile(args, context).toURI());
        //Creating the directory
        boolean bool = file.mkdir();
        if (bool) {
            return file.getName() + "Directory created successfully.";
        } else {
            return "Sorry couldn’t create specified directory";
        }
    }
}
