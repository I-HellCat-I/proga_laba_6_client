package CommandExecution.Commands;

import Classes.Context;
import Classes.SendedFlatUpdateRecord;
import CommandExecution.Command;
import Network.CommandMessage;

import java.io.IOException;


public class CommandUpdate extends Command {
    public CommandUpdate(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() throws IOException {
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            return "То, что вы ввели - не число";
        }
        context.getCommunicationsArray().sendMessage(new CommandMessage("CommandExecution.Commands.CommandCheckIfExists", id, null));
        boolean ans = context.getCommunicationsArray().getMessage(boolean.class);
        if (!ans){
            return "Квартиры с таким id не найдено";
        }
        context.getCommunicationsArray().sendMessage(new CommandMessage(this.getClass().getName(),-1,
                new SendedFlatUpdateRecord(id, context.getInteractor().inputFlat(true))));
        return context.getCommunicationsArray().getMessage(String.class);
    }
    public static String description(){
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
