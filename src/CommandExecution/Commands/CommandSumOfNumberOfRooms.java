package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;
import Network.CommandMessage;

import java.io.IOException;

public class CommandSumOfNumberOfRooms extends Command {
    public CommandSumOfNumberOfRooms(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() throws IOException {
        context.getCommunicationsArray().sendMessage(new CommandMessage(this.getClass().getName(),-1, null));
        return context.getCommunicationsArray().getMessage(String.class);
    }
    public static String description(){
        return "sum_of_number_of_rooms : вывести сумму значений поля numberOfRooms для всех элементов коллекции";
    }
}
