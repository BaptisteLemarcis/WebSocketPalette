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
        this.reader.connectionClosed();
    }

    @Override
    public void onError(Exception arg0)
    {
        this.reader.connectionClosed();
    }

    @Override
    public synchronized void onMessage(String arg0)
    {
        this.reader.pushMsg(arg0);
    }

    @Override
    public void onOpen(ServerHandshake arg0)
    {
    }
}
