package Network.Exceptions;

import java.rmi.server.ServerNotActiveException;

public class ServerNotRespondingException extends RuntimeException{
    public ServerNotRespondingException(){
        super("server is not responding");
    }
}
