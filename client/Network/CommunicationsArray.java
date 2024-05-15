package Network;

import Classes.Context;
import Network.Exceptions.ServerNotRespondingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.*;
import java.nio.channels.ConnectionPendingException;
import java.rmi.ServerError;
import java.rmi.ServerRuntimeException;
import java.rmi.server.ServerNotActiveException;
import java.util.Arrays;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;


public class CommunicationsArray {
    private Context context;
    protected DatagramSocket datagramSocket;
    protected JsonMapper mapper;
    protected InetAddress host;
    protected int serverPort;

    public CommunicationsArray(Context context, InetAddress host, int serverPort) throws IOException {
        this.context = context;
        this.datagramSocket = new DatagramSocket();
        datagramSocket.setSoTimeout(4000);
        this.host = host;
        this.serverPort = serverPort;
        mapper = JsonMapper.builder().findAndAddModules().build();
    }

    public <T> T getMessage(Class<T> toGet) throws IOException {
        DatagramPacket lengthPacket = new DatagramPacket(new byte[1000], 1000), messagePacket;
        datagramSocket.receive(lengthPacket);
        int length = mapper.readValue(lengthPacket.getData(), new TypeReference<Stack<Integer>>() {}).pop();
        messagePacket = new DatagramPacket(new byte[length], length);
        datagramSocket.receive(messagePacket);
        return mapper.readValue(messagePacket.getData(), toGet);
    }


    public void sendMessage(CommandMessage toSend) throws IOException {
        byte[] bytes = mapper.writeValueAsBytes(new Message(toSend, (InetSocketAddress) datagramSocket.getLocalSocketAddress()));
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, host, 3123);
        datagramSocket.send(packet);
    }

    public void handshake() throws IOException {
        sendMessage(new CommandMessage("CommandExecution.Commands.CommandHandShake", -1, null));
        datagramSocket.receive(new DatagramPacket(new byte[1000], 1000));
        datagramSocket.receive(new DatagramPacket(new byte[1000], 1000));
    }
}
