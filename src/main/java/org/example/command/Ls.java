package org.example.command;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

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
        buildHeader(allFiles, args);
        buildRow(allFiles, args);
        return "end of table";
    }

    private void buildRow(File[] allFiles, List<String> args) {
        String leftAlignFormat = "";
        leftAlignFormat = "| %-20s |%n";
        if (allFiles != null & args == null || args.isEmpty()) {
            for (File each : allFiles) {
                System.out.format(leftAlignFormat, each.getName());
                System.out.format("+----------------------+%n");
            }
        } else if (allFiles != null) {
            StringBuilder sb = new StringBuilder();
            for (File each : allFiles) {
                sb.append(each.getName());
//                List<Character> arguments = new ArrayList<>();
//                Collections.addAll(arguments, args.get(0).toCharArray());
                char[] character = args.get(0).toCharArray();
                for (char arg : character) {
                    if (arg == 's') {
                        for (File files : allFiles) {
                            sb.append("|").append(files.getUsableSpace());
                        }
                    }
                    if (arg == 'r') {
                        for (File files : allFiles) {
                            sb.append("|").append(files.canRead());
                        }
                    }
                    if (arg == 'w') {
                        for (File files : allFiles) {
                            sb.append("|").append(files.canWrite());
                        }
                    }
                    if (arg == 'e') {
                        for (File files : allFiles) {
                            sb.append("|").append(FilenameUtils.getExtension(String.valueOf(files)));
                        }
                    }
                }
                System.out.format(leftAlignFormat, sb);
            }
        }
        System.out.format("+----------------------+%n");
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
                System.out.format("|        File name     |       " + args.get(0).toCharArray()[0] + "   |%n");
                System.out.format("+----------------------+-------------+%n");
            }else if (argsLength == 2) {
                System.out.format("+----------------------+-------------+-------------+%n");
                System.out.format("|        File name     |   " + args.get(0).toCharArray()[0] + "   |   " +
                        args.get(0).toCharArray()[1] + "   |%n");
                System.out.format("+----------------------+-------------+-------------+%n");
            } else if (argsLength == 3) {
                System.out.format("+----------------------+-------------+-------------+-------------+%n");
                System.out.format("|        File name     |   " + args.get(0).toCharArray()[0] + "   |   " +
                        args.get(0).toCharArray()[1] +  "   |   " +
                        args.get(0).toCharArray()[2] + "   |%n");
                System.out.format("+----------------------+-------------+-------------+-------------+%n");
            } else if (argsLength == 4) {
                System.out.format("+----------------------+-------------+-------------+-------------+-------------+%n");
                System.out.format("|        File name     |   " + args.get(0).toCharArray()[0] + "   |   " +
                        args.get(0).toCharArray()[1] +  "   |   " +
                        args.get(0).toCharArray()[2] + "   |   " +
                        args.get(0).toCharArray()[3] + "   |%n");
                System.out.format("+----------------------+-------------+-------------+-------------+-------------+%n");
            }
        }
    }
}
