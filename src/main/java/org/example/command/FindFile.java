package org.example.command;

import java.io.File;
import java.util.List;

//шукаєм файл
public interface FindFile {

    public default File findFile(List<String> args, Context context) {
        File currentDirectory = context.getCurrentDirectory();
        // перевіряємо чи існує аргумент та чи існує файл
        if (args.isEmpty()) {
            return null;
        } else {
            File file = new File(currentDirectory, args.get(0));
            return file.exists() ? file : null;
        }
    }
}
