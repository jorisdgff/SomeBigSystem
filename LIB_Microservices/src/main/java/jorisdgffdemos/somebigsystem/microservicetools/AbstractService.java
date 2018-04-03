package jorisdgffdemos.somebigsystem.microservicetools;

import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;

public abstract class AbstractService {

    public void receiveEvent(Object event){

        handleEvent(event);
    }

    public abstract void handleEvent(Object event);
}
