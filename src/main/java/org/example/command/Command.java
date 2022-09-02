package org.example.command;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class Command {

    protected final Context context;

    public abstract String execute(List<String> args);

}
