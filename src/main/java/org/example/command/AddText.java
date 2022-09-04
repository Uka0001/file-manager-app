package org.example.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class AddText extends Command implements FindFile {
    public AddText(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        File file = findFile(args, context);
        System.out.println("Where do you want to add your text?" +
                "Enter: position or end");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        System.out.println("You chose: " + answer);
        if (answer.equalsIgnoreCase("end") & !args.isEmpty()) {
            return writeEnd(file);
        } else if (answer.equalsIgnoreCase("position") & !args.isEmpty()) {
            return writePosition(file);
        } else {
            return "Something went wrong." +
                    "Try again with new command and arguments";
        }
    }

    @SneakyThrows
    private String writeEnd(File file) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text that you want to \n" +
                "add to the end of the file");
        String textAdd = scanner.nextLine();
        try (FileWriter f = new FileWriter(file, true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {
            p.println(textAdd);
        } catch (IOException i) {
            i.printStackTrace();
        }
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    private String writePosition(File file) {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        System.out.println("Enter the position of" +
                " the text where you want to \n" +
                "add extra line");
        Integer position = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the text that you want to \n" +
                "add as extra line");
        String extraLine = scanner.nextLine();
        lines.add(position, extraLine);
        Files.write(file.toPath(), lines, StandardCharsets.UTF_8);
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}
