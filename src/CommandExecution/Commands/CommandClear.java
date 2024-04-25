package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;
import Network.CommandMessage;

import java.io.IOException;

public class CommandClear extends Command {
    public CommandClear(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() throws IOException {
        context.getCommunicationsArray().sendMessage(new CommandMessage(this.getClass().getName(),-1, null));
        return context.getCommunicationsArray().getMessage(String.class);
    }

    public static String description(){
        return "clear : очистить коллекцию";
    }
}
