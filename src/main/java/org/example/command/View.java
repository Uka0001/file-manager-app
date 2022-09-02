package org.example.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class View extends Command {
    public View(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        List listFiles = Arrays.asList(context.getCurrentDirectory().listFiles());
        //Search for [.txt] file in directory and pick:
        System.out.println("Enter .txt file name that you want to view from the list above");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        //Print file as string
        String string = FileUtils.readFileToString(new File(name));
        for (int i = 0; i < listFiles.size(); i++) {
            if (name.toLowerCase().trim().equals(listFiles.get(i))) {
                System.out.println("Read in: " + string);
            }
        }
        return string;
    }
}
