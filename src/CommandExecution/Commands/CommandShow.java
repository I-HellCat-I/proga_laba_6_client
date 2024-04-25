package CommandExecution.Commands;

import Classes.Context;
import Classes.Flat;
import Classes.House;
import CommandExecution.Command;
import Network.CommandMessage;

import java.io.IOException;
import java.util.ArrayList;

public class CommandShow extends Command {
    public CommandShow(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() throws IOException {
        context.getCommunicationsArray().sendMessage(new CommandMessage(this.getClass().getName(),-1, null));
        return context.getCommunicationsArray().getMessage(String.class);
    }
    public static String description(){
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
