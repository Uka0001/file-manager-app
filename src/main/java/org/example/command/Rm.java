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
        if (args.size() == 1) {
            // Delete file or directory only
            findFile(args, context).delete();
            return " - deleting successfully";
        } else if (args.get(1).equals("-r")) {
            //Delete dir with recursion, be carefully :)
            File directory = context.getCurrentDirectory();
            FileUtils.deleteDirectory(directory);
            return "Recursive deleting successfully";
        } else {
            return "Repeat your command and arguments";
        }
    }
}
