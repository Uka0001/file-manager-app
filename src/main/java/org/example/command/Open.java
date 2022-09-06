package org.example.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Open extends Command implements FindFile {
    public Open(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()){
            return "Enter the args pls.";
        }
        File file = findFile(args, context);
        if (file == null || !file.exists()) {
            return "File doesnt exist";
        }
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8) + " - your result.";
    }
}
