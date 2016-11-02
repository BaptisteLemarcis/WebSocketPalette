package ca.uqac.liara;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collection;

/**
 * Created by Baptiste on 10/11/2016.
 */
public class Server extends WebSocketServer {
    public Server(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
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
    public void onMessage(WebSocket webSocket, String s) {}

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        System.err.println("Server error");
        e.printStackTrace();
    }

    public void sendToAll( String text ) {
        Collection<WebSocket> con = connections();
        if(!con.isEmpty()) {
            synchronized ( con ) {
                for( WebSocket c : con ) {
                    c.send( text );
                }
            }
        }
    }
}
