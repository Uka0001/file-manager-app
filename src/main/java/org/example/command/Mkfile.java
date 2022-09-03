package org.example.command;

import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public class Mkfile extends Command {
    public Mkfile(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        String name = args.toString();
        //Creating a File object
        File file = new File(name);
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