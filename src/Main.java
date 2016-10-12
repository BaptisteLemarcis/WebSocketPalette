import ca.uqac.liara.JSonParser;
import ca.uqac.liara.WebSocketReader;
import ca.uqac.liara.WebSocketWriter;
import ca.uqac.liara.sources.RandomStreamer;
import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Pullable;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by Baptiste on 10/6/2016.
 */
public class Main {
    public static void main(String[] argv){
        try {
            RandomStreamer rs = new RandomStreamer();
            WebSocketWriter wsw = new WebSocketWriter(1,8080);
            Connector.connect(rs,wsw, 0, 0);
            wsw.start();
            System.out.println("RandomStreamer connected to ws");

            WebSocketReader wsr = new WebSocketReader(new URI("ws://localhost:8080"));
            Pullable p = wsr.getPullableOutput(0);
            while(p.hasNext() == Pullable.NextStatus.YES){
                System.out.println(p.pull());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Connector.ConnectorException e) {
            e.printStackTrace();
        }
    }
}
