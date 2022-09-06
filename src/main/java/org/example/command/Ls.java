package org.example.command;
/*
need fix, my method*/

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.List;

public class Ls extends Command {
    public Ls(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File file = context.getCurrentDirectory();
        File[] allFiles = file.listFiles();
        return buildTable(allFiles, args);
    }

    private String buildTable(File[] allFiles, List<String> args) {
        StringBuilder sb = new StringBuilder();
        buildHeader(allFiles, args);
        buildRow(allFiles, args, sb);
        return sb.toString();
    }

    private void buildRow(File[] allFiles, List<String> args, StringBuilder sb) {
        String leftAlignFormat = "";
        leftAlignFormat = "| %-20s |%n";
        if (allFiles != null & args == null || args.isEmpty()) {
            for (File each : allFiles) {
                System.out.format(leftAlignFormat, each.getName());
                System.out.format("+----------------------+%n");
            }
        } else if (allFiles != null) {

            for (File files : allFiles) {
                StringBuilder flag = new StringBuilder("");
                if (args.contains('s')) {
                        flag.append(files.getUsableSpace());
                    }
                    if (args.contains('r')) {
                        flag.append(files.canRead());
                    }
                    if (args.contains('w')) {
                        flag.append(files.canWrite());
                    }
                    if (args.contains('e')) {
                        flag.append(FilenameUtils.getExtension(String.valueOf(files)));
                    }

                sb.append(String.format(leftAlignFormat, files.getName(), flag.toString()));
            }
        }
    }

    private void buildHeader(File[] allFiles, List<String> args) {
        String leftAlignFormat = "";
        leftAlignFormat = "| %-20s |%n";
        if (args == null || args.isEmpty()) {
            System.out.format("+----------------------+%n");
            System.out.format("|        File name     |%n");
            System.out.format("+----------------------+%n");
        } else {
            int argsLength = args.get(0).toCharArray().length;
            if (argsLength == 1) {
                System.out.format("+----------------------+-------------+%n");
                System.out.format("|        File name     |       " + args.get(0).toCharArray()[0] + "     |%n");
                System.out.format("+----------------------+-------------+%n");
            } else if (argsLength == 2) {
                System.out.format("+----------------------+-------------+-------------+%n");
                System.out.format("|        File name     |" +
                        "       " + args.get(0).toCharArray()[0] + "     |" +
                        "       " + args.get(0).toCharArray()[1] + "     |%n");
                System.out.format("+----------------------+-------------+-------------+%n");
            } else if (argsLength == 3) {
                System.out.format("+----------------------+-------------+-------------+-------------+%n");
                System.out.format("|        File name     |       " + args.get(0).toCharArray()[0] + "     |" +
                        "       " + args.get(0).toCharArray()[1] + "     |" +
                        "       " + args.get(0).toCharArray()[2] + "     |%n");
                System.out.format("+----------------------+-------------+-------------+-------------+%n");
            } else if (argsLength == 4) {
                System.out.format("+----------------------+-------------+-------------+-------------+-------------+%n");
                System.out.format("|        File name     |       " + args.get(0).toCharArray()[0] + "     |" +
                        "       " + args.get(0).toCharArray()[1] + "     |" +
                        "       " + args.get(0).toCharArray()[2] + "     |       " + args.get(0).toCharArray()[3] + "     |%n");
                System.out.format("+----------------------+-------------+-------------+-------------+-------------+%n");
            }
        }
    }
}
