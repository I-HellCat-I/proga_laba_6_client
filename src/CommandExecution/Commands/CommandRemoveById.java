package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;
import Network.CommandMessage;

import java.io.IOException;

public class CommandRemoveById extends Command {
    public CommandRemoveById(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() throws IOException {
        Integer id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            return ("Неверный формат Id (Не целое число)");
        }
        context.getCommunicationsArray().sendMessage(new CommandMessage(this.getClass().getName(),Integer.parseInt(args[0]), null));
        return context.getCommunicationsArray().getMessage(String.class);
    }
    public static String description(){
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
