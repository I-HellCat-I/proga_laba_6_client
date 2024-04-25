package Network;

import Classes.Context;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;


public class CommunicationsArray {
    private Context context;
    protected DatagramSocket datagramSocket;
    protected Socket server;
    protected JsonMapper mapper;
    protected InetAddress host;
    protected int sendPort;
    protected int incomePort;
    protected DataInputStream inputStream;

    public CommunicationsArray(Context context, InetAddress host, int sendPort, int incomePort) throws IOException {
        this.context = context;
        this.datagramSocket = new DatagramSocket();
        this.host = host;
        this.sendPort = sendPort;
        this.incomePort = incomePort;
        this.server = new Socket("localhost", 3214);
        inputStream = new DataInputStream(server.getInputStream());
        mapper = JsonMapper.builder().findAndAddModules().build();
    }

    public <T> T getMessage(Class<T> toGet) throws IOException {
        return mapper.readValue((DataInput) inputStream, toGet);
    }


    public <T> void sendMessage(T toSend) throws IOException {
        byte[] bytes = mapper.writeValueAsBytes(toSend);
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("localhost"), 3213);
        datagramSocket.send(packet);
    }

}
