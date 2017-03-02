package ca.uqac.liara;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.tmf.Source;

import java.util.Queue;

/**
 * Created by baptiste on 2/9/2017.
 */
public class WSReaderProcessor extends Source {
    private Server m_server = null;
    private Client m_client = null;

    public WSReaderProcessor(Server s){
        super(1);
        this.m_server = s;
    }

    public WSReaderProcessor(Client c){
        super(1);
        this.m_client = c;
    }

    @Override
    protected Queue<Object[]> compute(Object[] inputs) {
        if(this.m_client != null && this.m_client.getConnection().isOpen()){
            for(;;){
                if(this.m_client.hasMessage()) break;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return wrapObject(this.m_client.getMessage());
        }

        for(;;) {
            if (this.m_server.hasMessage()) break;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return wrapObject(this.m_server.getMessage());
    }

    @Override
    public Processor clone() {
        if(this.m_client != null){
            return new WSReaderProcessor(this.m_client);
        }
        return new WSReaderProcessor(this.m_server);
    }
}
