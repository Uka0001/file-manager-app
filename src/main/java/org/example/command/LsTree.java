package org.example.command;

import java.io.File;
import java.util.List;

public class LsTree extends Command implements FindFile {
    public LsTree(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        // Додав варіант вибора папки:
        // з варіантом поточної папки - якщо не має аргументу і
        // з варіантом вибору папки якщо є аргумент
        File directory;
        if (args.isEmpty()){
            directory = context.getCurrentDirectory();
        } else {
            directory = new File(args.get(0));
        }
        if (!directory.isDirectory()) {
            return "Argument is not a Directory name";
        }
        int indent = 0;
        StringBuilder sb = new StringBuilder();
        printDirectoryTree(directory, indent, sb);
        return sb.toString();
    }

    private static void printDirectoryTree(File directory, int indent,
                                           StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(directory.getName());
        sb.append("/");
        sb.append("\n");
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                printDirectoryTree(file, indent + 1, sb);
            } else {
                printFile(file, indent + 1, sb);
            }
        }

    }

    private static void printFile(File file, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(file.getName());
        sb.append("\n");
    }

    private static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("|  ");
        }
        return sb.toString();
    }
}
