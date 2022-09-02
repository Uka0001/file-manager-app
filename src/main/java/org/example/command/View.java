package org.example.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class View extends Command implements FindFile {
    public View(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        File file = findFile(args);
        if (file == null || !file.exists()) {
            return "File doesnt exist";
        }
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    public File findFile(List<String> args) {
        if (args.isEmpty()) {
            return null;
        } else {
            String fileName = args.get(0);
            File currentDirectory = context.getCurrentDirectory();
            return new File(currentDirectory, fileName);
        }
    }
}
