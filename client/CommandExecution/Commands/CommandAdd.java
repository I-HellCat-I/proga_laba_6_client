package CommandExecution.Commands;

import Classes.Context;
import Classes.Flat;
import Classes.FlatUpdateRecord;
import Classes.SendedFlatUpdateRecord;
import CommandExecution.Command;
import Network.CommandMessage;

import java.io.IOException;


public class CommandAdd extends Command {
    public CommandAdd(String[] args, Context context) {
        super(args, context);
    }

    @Override
    public String execute() throws IOException {
        context.getCommunicationsArray().handshake();
        try{
            SendedFlatUpdateRecord toAdd = new SendedFlatUpdateRecord(-1, context.getInteractor().inputFlat(false));
            if (toAdd != null) {
                context.getCommunicationsArray().sendMessage(new CommandMessage(this.getClass().getName(),-1, toAdd));
                return context.getCommunicationsArray().getMessage(String.class);
            }
        } catch (NumberFormatException nfe){
            return "Не рекомендую пропускать числа.";
        }
        catch (RuntimeException e){
            return (e.getMessage() + " " + e.getClass());
        }
        return ("Что-то пошло не так, но что же?");
    }

    public static String description(){
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
