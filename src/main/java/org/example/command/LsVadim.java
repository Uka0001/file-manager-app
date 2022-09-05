package org.example.command;

/*
https://github.com/connor999jobs/FileSystem/blob/master/src/main/java/org/example/commands/impl/LsTree.java
https://github.com/connor999jobs/FileSystem implementation
TODO //TreeNode implementation
alternative version - https://stackoverflow.com/questions/10655085/print-directory-tree
there is also an answer with tree impl but there are TreeNode args so not as we have only one arg
see also here -https://stackoverflow.com/questions/3522454/how-to-implement-a-tree-data-structure-in-java#17490124
* */

import java.io.File;
import java.util.List;
import java.util.Objects;

public class LsVadim extends Command {
    public LsVadim(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        // проабгрейдив код Вадіма - connor999jobs, додав варіант вибора папки
        // з варіантом поточної папки якщо не має аргументу і
        // з варіантом вибору папки якщо є аргумент
        File folder;
        if (args.isEmpty()){
           folder = context.getCurrentDirectory();
        } else {
            folder = new File(args.get(0));
        }
        if (!folder.isDirectory()){
            throw new IllegalArgumentException("folder is not a Directory");
        }
        int in = 0;
        StringBuilder builder = new StringBuilder();
        printLSTree(folder,in, builder);
        return builder.toString();
    }

    private void printLSTree(File folder, int in, StringBuilder builder) {
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("folder is not a Directory");
        }
        builder.append(getIndentString(in));
        builder.append("├──");
        builder.append(folder.getName());
        builder.append("\n");
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory()) {
                printLSTree(file, in + 1, builder);
            } else {
                printFile(file, in + 1, builder);
            }
        }
    }

    private static void printFile(File file, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append("└──");
        sb.append(file.getName());
        sb.append("\n");
    }

    private static String getIndentString(int indent) {
        return "│  ".repeat(Math.max(0, indent));
    }
}
