package org.example.command;

import java.util.List;

public class Pwd extends Command {

    public Pwd(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        return context.getCurrentDirectory().getAbsolutePath() + "You are now here";
    }
}
