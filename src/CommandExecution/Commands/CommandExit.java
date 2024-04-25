package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;

import static java.lang.System.exit;

public class CommandExit extends Command {
    public CommandExit(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() {
        context.setExitCommandUsed();
        exit(1);
        return "Ok";
    }

    public static String description(){
        return "exit : завершить программу (без сохранения в файл)";
    }
}
