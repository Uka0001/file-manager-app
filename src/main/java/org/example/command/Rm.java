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
        if (args.get(0).equals("-r")) {
            //видаляємо рекурсивно поточну директорію
            File file = context.getCurrentDirectory();
            FileUtils.deleteDirectory(file);
            return "Recursive deleting successfully";
        } else {
            // видаляємо окремий файл або директорію
            if (new File(args.get(0)).exists()) {
                findFile(args, context).delete();
                return "Deleting successfully";
            } else {
                return "Reenter command and arguments pls." +
                        " Something vent wrong." +
                        "File or directory is not exist";
            }
        }
    }
}
