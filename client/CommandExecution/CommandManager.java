package CommandExecution;

import Classes.Context;
import CommandExecution.Commands.*;
import Network.Exceptions.ServerNotRespondingException;
import lombok.Getter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketTimeoutException;
import java.rmi.server.ServerNotActiveException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Класс, отвечающий за обработку команд
 *
 */
public class CommandManager {

    @Getter
    static HashMap<String, Class<? extends Command>> commands = new HashMap<>(); // Хранит в себе команды
    private Context context;
    public CommandManager(Context context){
        this.context = context;
    }

    /*
      Блок, задающий базовые команды
      */
    static {
        addCommand("help", CommandHelp.class);
        addCommand("info", CommandInfo.class);
        addCommand("show", CommandShow.class);
        addCommand("add", CommandAdd.class);
        addCommand("sort", CommandSort.class);
        addCommand("update", CommandUpdate.class);
        addCommand("remove_by_id", CommandRemoveById.class);
        addCommand("clear", CommandClear.class);
        addCommand("remove_at", CommandRemoveAt.class);
        addCommand("remove_last", CommandRemoveLast.class);
        addCommand("count_less_than_furnish", CommandCountLTFurnish.class);
        addCommand("remove_by_id", CommandRemoveById.class);
        addCommand("execute_script", CommandExecuteScript.class);
        addCommand("sum_of_number_of_rooms", CommandSumOfNumberOfRooms.class);
        addCommand("print_unique_house", CommandPrintUniqueHouse.class);
        addCommand("exit", CommandExit.class);
    }

    public static void addCommand(String s, Class<? extends Command> f) {
        commands.put(s, f);
    }

    public String exec(String type, String[] args) {
        try {
            String commandAnswer = (commands.get(type).getConstructor(String[].class, Context.class)
                    .newInstance((Object) (args == null ? new String[]{""} : args), context)).execute();
            if (commandAnswer != null){
                return commandAnswer;
            }
            return (context.getCommunicationsArray().getMessage(String.class));
        }catch (SocketTimeoutException e){
            return "Сервер не отвечает. Уведомите администратора сервера.";
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e.getMessage());
        }

    }

}
