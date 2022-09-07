package org.example.command;

import java.io.File;
import java.util.List;

public class MkDir extends Command {
    public MkDir(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        //Creating a File object
        //Check if args empty or not
        File file = new File(args.get(0));
        //Creating the directory
        boolean bool = file.mkdir();
        if (bool) {
            return file.getName() + " - directory created successfully";
        } else {
            return "Sorry couldn’t create specified directory";
        }
    }
}
