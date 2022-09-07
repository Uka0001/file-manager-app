package org.example.command;

import java.util.List;

public class Find extends Command implements FindFile {
    public Find(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty() || args.get(0).isEmpty()) {
            return "Enter the args pls.";
        } else {
            findFile(args, context);
        }
        return findFile(args, context).getAbsolutePath() + " - your result.";
    }
}
