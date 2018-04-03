package jorisdgffdemos.somebigsystem.microservicetools;

import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;

public abstract class AbstractEventHandler {

    public abstract String handleEvent(AbstractEvent event);
}
