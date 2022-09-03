package org.example.command;

import java.io.File;
import java.util.List;

public class LsTable extends Command {
    public LsTable(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File file = context.getCurrentDirectory();
        File[] allFiles = file.listFiles();
        String leftAlignFormat = "";
        if (args == null || args.isEmpty()) {
            leftAlignFormat = "| %-20s |%n";
            System.out.format("+----------------------+%n");
            System.out.format("|        File name     |%n");
            System.out.format("+----------------------+%n");
        } else {
            int argsLength = args.get(0).toCharArray().length;
            if (argsLength == 1) {
                leftAlignFormat = "| %-20s | %-4d |%n";
                System.out.format("+----------------------+-------------+%n");
                System.out.format("|        File name     |   " + args.get(0).toCharArray()[0] + "   |%n");
                System.out.format("+----------------------+-------------+%n");
            }else if (argsLength == 2) {
                leftAlignFormat = "| %-20s | %-4d | %-4d |%n";
                System.out.format("+----------------------+-------------+%n");
                System.out.format("|        File name     |   " + args.get(0).toCharArray()[0] + "   |   " +
                        args.get(0).toCharArray()[1] + "   |%n");
                System.out.format("+----------------------+-------------+%n");
            } else if (argsLength == 3) {
                leftAlignFormat = "| %-20s | %-4d | %-4d | %-4d |%n";
                System.out.format("+----------------------+-------------+%n");
                System.out.format("|        File name     |   " + args.get(0).toCharArray()[0] + "   |   " +
                        args.get(0).toCharArray()[1] +  "   |   " +
                        args.get(0).toCharArray()[2] + "   |%n");
                System.out.format("+----------------------+-------------+%n");
            } else if (argsLength == 4) {
                leftAlignFormat = "| %-20s | %-4d | %-4d | %-4d | %-4d | %n";
                System.out.format("+----------------------+-------------+%n");
                System.out.format("|        File name     |   " + args.get(0).toCharArray()[0] + "   |   " +
                        args.get(0).toCharArray()[1] +  "   |   " +
                        args.get(0).toCharArray()[2] + "   |   " +
                        args.get(0).toCharArray()[3] + "   |%n");
                System.out.format("+----------------------+-------------+%n");
            }
        }

        if (allFiles != null & args == null || args.isEmpty()) {
            for (File each : allFiles) {
                System.out.format(leftAlignFormat, each.getName());
                System.out.format("+----------------------+%n");
            }
        } else if (allFiles != null) {
            for (File each : allFiles) {
                char[] character = args.get(0).toCharArray();
                System.out.format(leftAlignFormat, each.getName());
                for (char arg : character) {
                    if (arg == 's') {
                        for (File files : allFiles) {
                            System.out.format(leftAlignFormat, files.getUsableSpace());
                        }
                    }
                    if (arg == 'r') {
                        for (File files : allFiles) {
                            System.out.format(leftAlignFormat, files.canRead());
                        }
                    }
                    if (arg == 'w') {
                        for (File files : allFiles) {
                            System.out.format(leftAlignFormat, files.canWrite());
                        }
                    }
                    if (arg == 'e') {
                        for (File files : allFiles) {
                            System.out.format(leftAlignFormat, files.getName(), files.isFile());
                        }
                    }
                }
            }
        }
        System.out.format("+----------------------+%n");
        return "end of table";
    }
}
