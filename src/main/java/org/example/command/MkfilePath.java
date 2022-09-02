package org.example.command;

import lombok.SneakyThrows;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Mkfile extends Command{
    public Mkfile(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        System.out.println("Enter the path to create a file: ");
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        System.out.println("Enter the name of the desired file: ");
        path = path+sc.next();
        //Creating a File object
        File file = new File(path);
        //Creating the file
        boolean bool = file.createNewFile();
        if(bool){
            System.out.println("File created successfully");
        }else{
            System.out.println("Sorry couldn’t create specified file");
        }
        return file.toString();
    }
}
