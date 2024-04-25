package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;
import Network.CommandMessage;

import java.io.IOException;

public class CommandCountLTFurnish extends Command {
    public CommandCountLTFurnish(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() throws IOException {
        context.getCommunicationsArray().sendMessage(new CommandMessage(this.getClass().getName(),Integer.parseInt(args[0]),
                null));
        return context.getCommunicationsArray().getMessage(String.class);
    }
    public static String description(){
        return "count_less_than_furnish furnish : вывести количество элементов, значение поля furnish которых меньше заданного";
    }
}
