package jorisdgffdemos.somebigsystem.microservicetools;

import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;

public abstract class AbstractAggregate {

    private AbstractService listener;

    public void setListener(AbstractService listener){

        this.listener = listener;
    }

    protected void emitEvent(Object event){

        listener.receiveEvent(event);
    }
}