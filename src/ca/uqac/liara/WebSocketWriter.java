package ca.uqac.liara;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.Pushable;
import ca.uqac.lif.cep.SingleProcessor;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.server.WebSocketServer;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Queue;

/**
 * Created by Baptiste on 10/6/2016.
 */
public class WebSocketWriter extends SingleProcessor implements Runnable{
    private Server server;
    private int port;
    private boolean serverRunning = false;

    public WebSocketWriter(int port) {
        super(1,0);
        this.port = port;
        try {
            this.server = new Server(port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Queue<Object[]> compute(Object[] inputs)
    {
        this.server.sendToAll((String)inputs[0]);
        return null;
    }

    public void start(){
        this.server.start();
        this.serverRunning = true;
    }

    @Override
    public Processor clone()
    {
        return new WebSocketWriter(this.port);
    }

    @Override
    public void run() {
        while(this.serverRunning){
            boolean toContinue = false;
            for (int i = 0; i < m_inputArity; i++)
            {
                Pullable p = m_inputPullables[i];
                Pullable.NextStatus status = p.hasNext();
                if (status == Pullable.NextStatus.NO)
                {
                    toContinue = true;
                }
                if (status == Pullable.NextStatus.MAYBE)
                {
                    toContinue = true;
                }
            }

            if(toContinue) continue;

            Object[] inputs = new Object[m_inputArity];
            {
                int i = 0;
                for (Pullable p : m_inputPullables)
                {
                    inputs[i] = p.pull();
                    i++;
                }
            }

            // Compute output event
            this.compute(inputs);
        }
    }

    public void stop() {
        try {
            this.server.stop();
            this.serverRunning = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
