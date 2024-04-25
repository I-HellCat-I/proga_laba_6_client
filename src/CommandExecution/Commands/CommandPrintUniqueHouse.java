package CommandExecution.Commands;

import Classes.Context;
import Classes.House;
import CommandExecution.Command;
import Network.CommandMessage;
import Network.CommunicationsArray;

import java.io.IOException;
import java.util.ArrayList;

public class CommandPrintUniqueHouse extends Command {
    public CommandPrintUniqueHouse(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() throws IOException {
        context.getCommunicationsArray().sendMessage(new CommandMessage(this.getClass().getName(),-1, null));
        return context.getCommunicationsArray().getMessage(String.class);
    }
    public static String description(){
        return "print_unique_house : вывести уникальные значения поля house всех элементов в коллекции";
    }
}
