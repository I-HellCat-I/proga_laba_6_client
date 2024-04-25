package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;
import CommandExecution.Interactor;
import Network.CommandMessage;

import java.io.IOException;

public class CommandRemoveAt extends Command {
    public CommandRemoveAt(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() throws IOException {
        context.getCommunicationsArray().sendMessage(new CommandMessage(this.getClass().getName(),Integer.parseInt(args[0]), null));
        return context.getCommunicationsArray().getMessage(String.class);
    }
    public static String description(){
        return "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)";
    }
}
