package org.example.command;


import java.io.File;
import java.util.List;

public class ArgsUtils {

    public static boolean hasFirstArg(List<String> args) {
        return args != null && !args.isEmpty() && args.get(0) != null || !args.get(0).isBlank();
    }

    private static boolean hasAnyFlags(List<String> args) {
        return args != null && !args.isEmpty() && args.stream().anyMatch(s -> s.contains("-"));
    }

    public static boolean hasFlags(List<String> args, String... flags) {
        if (!hasAnyFlags(args)) {
            return false;
        } else {
            for (String arg : args) {
                for (String flag : flags) {
                    if (arg.matches(flag)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String firstParamMissingMessage(Class<? extends Command> command) {
        return "Argument missing. Try " + Command.COLOR_KEYS.ANSI_GREEN + command.getSimpleName().toLowerCase() + " anyname" + Command.COLOR_KEYS.ANSI_RESET;
    }

    public static boolean isRWAccessable(File file) {
        if (!file.exists()) {
            System.out.println("The file does not exist or cannot be found");
            return false;
        }
        if (file.isDirectory()) {
            System.out.println("The file is directory. Cannot be opened");
            return false;
        }
        if (!file.canWrite()) {
            System.out.println("Missing permissions. Cannot be edited");
            return false;
        }
        if (!file.canRead()) {
            System.out.println("Missing permissions. Cannot be read");
            return false;
        }
        return true;
    }
}
