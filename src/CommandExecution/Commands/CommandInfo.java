package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;
import Network.CommandMessage;
import Network.CommunicationsArray;

import java.io.IOException;

public class CommandInfo extends Command {
    public CommandInfo(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() throws IOException {
        context.getCommunicationsArray().sendMessage(new CommandMessage(this.getClass().getName(),-1, null));
        return context.getCommunicationsArray().getMessage(String.class);
    }
    public static String description(){
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
