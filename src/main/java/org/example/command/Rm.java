package org.example.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class Rm extends Command implements FindFile {

    public Rm(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        if (findFile(args, context).equals("-r")) {
            //Delete dir with recursion
            File file = context.getCurrentDirectory();
            FileUtils.deleteDirectory(file);
            return "Recursive deleting successfully";
        } else {
            // Delete file or directory only
            findFile(args, context).delete();
            return "Deleting successfully";
        }
    }
}
