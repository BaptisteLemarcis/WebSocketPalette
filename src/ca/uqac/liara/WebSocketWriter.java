package ca.uqac.liara;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;


import java.util.Queue;

/**
 * Created by Baptiste on 10/6/2016.
 */
public class WebSocketWriter extends SingleProcessor {
    private String uri;

    public WebSocketWriter(int in_arity, String uri) {
        super(in_arity, 0);
        this.uri = uri;
    }

    @Override
    protected Queue<Object[]> compute(Object[] objects) {
        return null;
    }

    @Override
    public Processor clone() {
        return null;
    }
}
