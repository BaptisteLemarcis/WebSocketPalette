package ca.uqac.liara;

import ca.uqac.lif.cep.BeepBeepUnitTest;
import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.epl.QueueSink;
import ca.uqac.lif.cep.epl.QueueSource;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Baptiste on 10/6/2016.
 */
public class WebSocketTest extends BeepBeepUnitTest {
    private static final int port = 45652;

    @Test
    public void testRead1() throws URISyntaxException, InterruptedException, IOException, Connector.ConnectorException {
        QueueSource ones = new QueueSource("1", 1);
        Vector<Object> obj = new Vector<Object>();
        for(int i = 0; i < 20; i++) obj.add("1");
        ones.setEvents(obj);
        WebSocketWriter wsw = new WebSocketWriter(port);
        Connector.connect(ones, wsw, 0, 0);
        wsw.start();
        new Thread(wsw).start();
        Thread.sleep(120);
        WebSocketReader wsr = new WebSocketReader(new URI("ws://localhost:" + port));
        QueueSink sink = new QueueSink(1);
        Connector.connect(wsr, sink);
        sink.pull();
        String recv = (String) sink.remove()[0];
        if (recv == null || !recv.equals("1")) {
            fail("Expected 1 on pull, got " + recv);
        }
        wsr.stop();
        wsw.stop();
        assertTrue(true);
    }
}
