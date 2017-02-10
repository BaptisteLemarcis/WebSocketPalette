package ca.uqac.liara;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

/**
 * Created by Baptiste on 10/11/2016.
 */
public class Server extends WebSocketServer {
    private Queue<String> m_messageQueue;

    public Server(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        m_messageQueue = new ArrayDeque<String>();
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("Server open");
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("Server closed");
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        this.m_messageQueue.add(s);
    }

    public String getMessage(){
        return m_messageQueue.remove();
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        System.err.println("Server error");
        e.printStackTrace();
    }

    public void sendToAll( String text ) {

        this.connections();

        Collection<WebSocket> con = connections();
        if(con!=null && !con.isEmpty()) {
            synchronized ( con ) {
                for( WebSocket c : con ) {
                    c.send( text );
                }
            }
        }
    }
}
