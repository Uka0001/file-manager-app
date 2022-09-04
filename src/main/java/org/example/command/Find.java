package org.example.command;

import java.io.File;
import java.util.List;

public class Find extends Command implements FindFile {
    public Find(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        return findFile(args, context).getAbsolutePath();
    }
}
