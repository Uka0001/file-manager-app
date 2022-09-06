package org.example.command;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.mutable.MutableInt;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LsVlad extends Command {

    public LsVlad(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        args = args.isEmpty() ? args : Arrays.asList(args.get(0).split(""));
        List<List<String>> result = new ArrayList<>();
        result.add(createHeaders(args));
        result.addAll(createBody(args));
        printTable(result, getPreCalculatedStringFormat(result));
        return "";
    }

    private void printTable(List<List<String>> result, String format) {
        result.forEach(row -> {
            System.out.printf((format) + "%n", row.toArray());
        });
    }

    public String getPreCalculatedStringFormat(List<List<String>> columns) {
        int columnsCount = columns.get(0).size();
        List<Integer> result = new ArrayList<>();
        for (MutableInt column = new MutableInt(0);
             column.getValue() < columnsCount; column.increment()) {
            result.add(columns.stream().
                    map(row -> row.get(column.getValue())).
                    map(String::length).
                    max(Integer::compare).get());
        }
        return "| " + result.stream().map(v -> "%" + v + "s").collect(Collectors.joining(" | ")) + " |";
    }

    private List<List<String>> createBody(List<String> args) {
        List<List<String>> body = new ArrayList<>();
        for (File f : Objects.requireNonNull(context.getCurrentDirectory().listFiles())) {
            body.add(buildRow(args, f));
        }
        return body;
    }

    private List<String> buildRow(List<String> args, File file) {
        List<String> row = new ArrayList<>();
        row.add(file.getName());
        for (String flag : args) {
            switch (flag) {
                case "s" -> row.add(String.valueOf(FileUtils.sizeOf(file)));
                case "r" -> row.add(String.valueOf(file.canRead()));
                case "w" -> row.add(String.valueOf(file.canWrite()));
                case "e" -> row.add(file.isHidden() ? "" : FilenameUtils.getExtension(file.getName()));
            }
        }
        return row;
    }

    private List<String> createHeaders(List<String> args) {
        List<String> header = new ArrayList<>();
        header.add("File name");
        for (String flag : args) {
            switch (flag) {
                case "s" -> header.add("Size");
                case "r" -> header.add("Readable");
                case "w" -> header.add("Writable");
                case "e" -> header.add("Extension");
            }
        }
        return header;
    }
}
