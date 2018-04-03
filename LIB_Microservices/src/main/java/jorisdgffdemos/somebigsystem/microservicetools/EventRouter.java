package jorisdgffdemos.somebigsystem.microservicetools;

import jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.events.POCreatedEvent;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;
import jorisdgffdemos.somebigsystem.crosscuttingconcerns.Utils;
import jorisdgffdemos.somebigsystem.domain.DomainEnums;

import java.util.ArrayList;
import java.util.List;

public class EventRouter {

    private static EventRouter instance;

    private EventRouter(){

    }

    public void route(AbstractEvent event){

        if(event instanceof POCreatedEvent){

            String topic = "QUERY";
            String key = DomainEnums.Type.PURCHASEORDER + "_" + "CREATED";

            //StreamEmitter.getInstance().send(event, topic, key);
        }
    }

    public static EventRouter getInstance(){

        if(instance == null){

            instance = new EventRouter();
        }

        return instance;
    }
}