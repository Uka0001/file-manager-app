package org.example.command;

import java.io.File;
import java.util.List;

public class Find extends Command {
    public Find(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return null;
        } else {
            String fileName = args.get(0);
            File currentDirectory = context.getCurrentDirectory();
            return new File(currentDirectory, fileName).getAbsolutePath();
        }
    }
}
