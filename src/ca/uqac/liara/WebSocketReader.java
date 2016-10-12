package ca.uqac.liara;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.Pushable;
import ca.uqac.lif.cep.SingleProcessor;
import ca.uqac.lif.cep.epl.Source;
import jdk.nashorn.internal.parser.JSONParser;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Reads chunks of data from a websocket.
 * These chunks are returned as events in the form of strings.
 * This processor <strong>pushes</strong> events downstream.
 * You will not get anything if you use its output pullables.
 *
 * @author Sylvain Hallé
 */
public class WebSocketReader extends Source
{
    /**
     * The URI of the web socket server
     */
    private URI m_serverUri;
    private Queue<String> m_messageQueue;
    private Client client;

    WebSocketReader()
    {
        super(1);
        this.m_messageQueue = new ArrayDeque<String>();
    }

    /**
     * Creates a new web socket reader
     * @param server_uri The URI of the server this reader should connect to
     */
    public WebSocketReader(URI server_uri)
    {
        this();
        m_serverUri = server_uri;
        client = new Client(m_serverUri, this);
        try {
            client.connectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Queue<Object[]> compute(Object[] inputs)
    {
        while(m_messageQueue.isEmpty()) try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wrapObject(m_messageQueue.remove());
    }

    @Override
    public Processor clone()
    {
        return new WebSocketReader(m_serverUri);
    }

    public void pushMsg(String arg0) {
        this.m_messageQueue.add(arg0);
    }
}