package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;

public class CommandExecuteScript extends Command {
    public CommandExecuteScript(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() {
        return context.getInteractor().executeScript(args[0]);
    }

    public static String description(){
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
