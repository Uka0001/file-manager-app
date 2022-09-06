package org.example.command;

import java.io.File;
import java.util.List;

public class Cd extends Command {

    public Cd(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Incorrect argument. Use `..` to navigate to parent directory or choose right child directory";
        } else {
            String direction = args.get(0);
            File currentDirectory = context.getCurrentDirectory();

            if (direction.equals("..")) {
                context.setCurrentDirectory(currentDirectory.getParentFile());
            } else {
                File child = new File(currentDirectory, direction);
                if (!child.exists()) {
                    return "Child directory " + direction + " does not exist";
                } else {
                    context.setCurrentDirectory(child);
                }
            }
        }
        // виводжу папку в якій знаходимся
        return context.getCurrentDirectory().toString();
    }
}
