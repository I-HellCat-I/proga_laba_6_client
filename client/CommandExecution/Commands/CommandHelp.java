package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;
import CommandExecution.CommandManager;

import java.lang.reflect.InvocationTargetException;

public class CommandHelp extends Command {
    public CommandHelp(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() {
        StringBuilder res = new StringBuilder();
        CommandManager.getCommands().forEach((s, c) -> {
            try {
                res.append(c.getMethod("description").invoke(null) + "\n");
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return res.toString();
    }

    public static String description(){
        return "help : вывести справку по доступным командам";
    }
}
