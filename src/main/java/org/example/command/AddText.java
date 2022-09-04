package org.example.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
        System.out.println("Enter the text that you want to \n" +
                "add to the end of the file");
        Scanner scanner = new Scanner(System.in);
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
}
