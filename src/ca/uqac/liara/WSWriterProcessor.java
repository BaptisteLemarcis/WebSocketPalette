package ca.uqac.liara;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.SingleProcessor;

import java.util.Queue;

/**
 * Created by baptiste on 2/9/2017.
 */
public class WSWriterProcessor extends SingleProcessor {
    private Server m_server = null;
    private Client m_client = null;

    public WSWriterProcessor(Server s) {
        super(1, 0);
        this.m_server = s;
    }

    public WSWriterProcessor(Client c) {
        super(1, 0);
        this.m_client = c;
    }

    @Override
    protected Queue<Object[]> compute(Object[] inputs) {
        if (this.m_client != null) {
            for (; ; ) {
                if (this.m_client.getConnection().isOpen()) break;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.m_client.send("" + inputs[0]);
            return null;
        }
        for (; ; ) {
            if (this.m_server.hasClient()) break;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.m_server.sendToAll("" + inputs[0]);
        return null;
    }

    public void send() throws InterruptedException {
        Pullable p;

        for (; ; ) {
            p = getPullableInput(0);
            if (p != null && p.hasNext()) break;
            Thread.sleep(1);
        }
        Object[] inputs = new Object[m_inputArity];
        {
            inputs[0] = p.pull();
        }

        // Compute output event
        this.compute(inputs);
    }

    @Override
    public Processor clone() {
        if (this.m_client != null) {
            return new WSWriterProcessor(this.m_client);
        }
        return new WSWriterProcessor(this.m_server);
    }
}
