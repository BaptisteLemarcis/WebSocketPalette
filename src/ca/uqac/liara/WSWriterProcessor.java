package ca.uqac.liara;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.SingleProcessor;

import java.util.Queue;

/**
 * Created by baptiste on 2/9/2017.
 */
public class WSWriterProcessor extends SingleProcessor implements Runnable{
    private Server m_server = null;
    private Client m_client = null;

    public WSWriterProcessor(Server s){
        super(1, 0);
        this.m_server = s;
    }

    public WSWriterProcessor(Client c){
        super(1, 0);
        this.m_client = c;
    }

    @Override
    protected Queue<Object[]> compute(Object[] inputs) {
        if(this.m_client != null && this.m_client.getConnection().isOpen()){
            this.m_client.send((String)inputs[0]);
        }
        this.m_server.sendToAll((String)inputs[0]);
        return null;
    }

    @Override
    public void run() {
        while(true){
            boolean toContinue = false;
            System.out.println("Arity " + m_inputArity);
            Pullable p = m_inputPullables[0];
            System.out.println("length " + m_inputPullables.length);
            System.out.println(" --> " + p);
            if(!p.hasNext()) toContinue = true;
            if(toContinue) continue;

            Object[] inputs = new Object[m_inputArity];
            {
                inputs[0] = p.pull();
            }

            // Compute output event
            this.compute(inputs);
        }
    }

    @Override
    public Processor clone() {
        if(this.m_client != null){
            return new WSWriterProcessor(this.m_client);
        }
        return new WSWriterProcessor(this.m_server);
    }
}
