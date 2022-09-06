package org.example.command;

import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public class MkFile extends Command implements FindFile {
    public MkFile(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()){
            return "Enter the args pls.";
        }
        String name = args.get(0);
        //Creating a File object
        File file = new File(name);
        findFile(args, context);
        //Creating the file
        boolean bool = file.createNewFile();
        if (bool) {
            System.out.println("File created successfully");
        } else {
            System.out.println("Sorry couldn’t create specified file");
        }
        return file.toString();
    }
}
