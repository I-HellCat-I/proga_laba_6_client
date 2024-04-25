package CommandExecution;

import Classes.Context;

import java.io.IOException;

/**
 * База для всех команд.
 */
public abstract class Command {

    protected String[] args;
    protected Context context;

    public Command(String[] args, Context context) {
        this.args = args;
        this.context = context;
    }

    public abstract String execute() throws IOException;

    public static String description(){
        return "desc";
    }
}
