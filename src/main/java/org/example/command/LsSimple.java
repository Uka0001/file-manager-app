package org.example.command;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LsSimple extends Command {
    public LsSimple(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File file = context.getCurrentDirectory();
        File[] allFiles = file.listFiles();
        StringBuilder result = new StringBuilder();
        if (allFiles != null) {
            for (File each : allFiles) {
                result.append(each.getName()).append(":");
                result.append(table(each.getName().length()));
                result.append(each.getUsableSpace());
                if (args.isEmpty())
                    result.append("\n");
                else {
                    if (args.get(0).equals("-s"))
                        size(result, each);
                    if (args.get(0).equals("-p")) {
                        permission(result, each);
                    }

                }
            }
        }
        return result.toString();
    }

    @SneakyThrows
    private void size(StringBuilder result, File each) {
        result.append(table(10));
        Path path = Paths.get(each.getPath());
        long size = Files.size(path);
        String s = String.format("%,d bytes", size);
        result.append(s).append("\n");
    }

    private void permission(StringBuilder result, File file) {
        result.append(table(10));
        if (file.canRead())
            result.append("r");
        if (file.canWrite())
            result.append("w");
        if (file.canExecute())
            result.append("x");
        result.append("\n");
    }

    private String table(int length) {
        String s = "";
        for (int i = 0; i < 20 - length; i++) {
            s += "-";
        }
        return s;
    }
}
