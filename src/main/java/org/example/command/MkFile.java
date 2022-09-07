package org.example.command;

import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public class MkFile extends Command {
    public MkFile(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        //Creating a File object
        //Creating a File with not null args
        File file = new File(args.get(0));
        //Creating the file
        boolean bool = file.createNewFile();
        if (bool) {
            return file.getName() + " - file created successfully";
        } else {
            return "Sorry couldn’t create specified file";
        }
    }
}
