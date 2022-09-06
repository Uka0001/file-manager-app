package org.example.command;

import java.io.File;
import java.util.List;

public class LsTree extends Command implements FindFile {
    public LsTree(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File directory = context.getCurrentDirectory();
        int indent = 0;
        StringBuilder sb = new StringBuilder();
        printDirectoryTree(args, directory, indent, sb, 0);
        return sb.toString();
    }

    private static void printDirectoryTree(List<String> args, File directory, int indent,
                                           StringBuilder sb, int depth) {
        // додав перевірку на порожній аргумент,
        // щоб не ловити нуль поінт ексепшин
        int askedDepth;
        if (!args.isEmpty()){
            askedDepth = Integer.parseInt(args.get(0));
        } else {
            askedDepth = 1;
        }

        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(directory.getName());
        sb.append("/");
        sb.append("\n");
        // Оптимізував трошки код, щоб не було дублювання умов
        for (File file : directory.listFiles()) {

            if (file.isDirectory() & !args.isEmpty() & ++depth < askedDepth) {
                printDirectoryTree(args, file, indent + 1, sb, depth);

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
        return "|  ".repeat(Math.max(0, indent));
    }
}
