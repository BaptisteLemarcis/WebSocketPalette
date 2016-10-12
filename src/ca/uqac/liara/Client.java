package ca.uqac.liara;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * Created by Baptiste on 10/11/2016.
 */
public class Client extends WebSocketClient {
    private WebSocketReader reader;
    public Client(URI serverURI, WebSocketReader w)
    {
        super(serverURI);
        this.reader = w;
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2)
    {
        // Do nothing
    }

    @Override
    public void onError(Exception arg0)
    {
        // Do nothing
    }

    @Override
    public synchronized void onMessage(String arg0)
    {
        //System.out.println("toto");
        this.reader.pushMsg(arg0);
        //System.out.println(m_messageQueue.size());
    }

    @Override
    public void onOpen(ServerHandshake arg0)
    {
        System.out.println("titi");
    }
}
