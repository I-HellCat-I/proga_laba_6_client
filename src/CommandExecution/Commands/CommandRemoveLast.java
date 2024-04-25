package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;
import Network.CommandMessage;

import java.io.IOException;
import java.util.EmptyStackException;

public class CommandRemoveLast extends Command {

    public CommandRemoveLast(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() throws IOException {
        context.getCommunicationsArray().sendMessage(new CommandMessage(this.getClass().getName(),-1, null));
        return context.getCommunicationsArray().getMessage(String.class);
    }
    public static String description(){
        return "remove_last : удалить последний элемент из коллекции";
    }
}
