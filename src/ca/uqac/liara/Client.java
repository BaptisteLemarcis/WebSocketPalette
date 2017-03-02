package ca.uqac.liara;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Baptiste on 10/11/2016.
 */
public class Client extends WebSocketClient {
    //private WebSocketReader reader;
    private Queue<String> m_messageQueue;
    public Client(URI serverURI)
    {
        super(serverURI);
        m_messageQueue = new ArrayDeque<String>();
        //this.reader = w;
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2)
    {
        System.out.println("Client closed");
        //this.reader.connectionClosed();
    }

    @Override
    public void onError(Exception arg0)
    {
        System.out.println("Client error");
        //arg0.printStackTrace();
        //this.reader.connectionClosed();
    }

    public String getMessage(){
        return m_messageQueue.remove();
    }

    @Override
    public synchronized void onMessage(String arg0)
    {
        this.m_messageQueue.add(arg0);
    }

    @Override
    public void onOpen(ServerHandshake arg0)
    {
        System.out.println("Client open");
    }

    public boolean hasMessage() {
        return !this.m_messageQueue.isEmpty();
    }
}
