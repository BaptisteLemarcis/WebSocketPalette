package ca.uqac.liara;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;

import java.util.Queue;

/**
 * Created by Baptiste on 10/6/2016.
 */
public class WebSocketWriter extends SingleProcessor {

    public WebSocketWriter(int in_arity, int out_arity) {
        super(in_arity, out_arity);
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
