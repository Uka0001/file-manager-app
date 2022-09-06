package org.example.command;

import java.io.File;
import java.util.List;

// Find file and check if args is not empty
public interface FindFile {

    public default File findFile(List<String> args, Context context) {
        File currentDirectory = context.getCurrentDirectory();
        // Check if args is not empty
        if (args.isEmpty()) {
            return null;
        } else {
            File file = new File(currentDirectory, args.get(0));
            return file.exists() ? file : null;
        }
    }
}
