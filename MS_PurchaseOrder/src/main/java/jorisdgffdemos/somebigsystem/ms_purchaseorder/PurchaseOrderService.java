package jorisdgffdemos.somebigsystem.ms_purchaseorder;

import jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.commands.*;

import jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.events.POCreatedEvent;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractCommand;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;
import jorisdgffdemos.somebigsystem.crosscuttingconcerns.Utils;
import jorisdgffdemos.somebigsystem.microservicetools.*;

import java.util.HashMap;

public class PurchaseOrderService extends AbstractService implements StreamReaderCallback {

    private HashMap<String, Class> hashMap;

    private PurchaseOrderService() {

        hashMap = new HashMap<>();
        //TODO naar config file verplaatsen
        hashMap.put("POCreateCommand", POCreateCommand.class);
        hashMap.put("POIncreaseAmountCommand", POIncreaseAmountCommand.class);

        StreamReader.getInstance("purchaseorderservice", "PURCHASEORDER", this);
    }

    @Override
    public void eventReceived(Message message, String content) {

        AbstractCommand command = (AbstractCommand) Utils.parseJsonToObject(content, hashMap.get(message.type));
        PurchaseOrder purchaseOrder = PORepository.getInstance().getPurchaseOrder(command.code);
        purchaseOrder.setListener(this);
        purchaseOrder.handle(command);
    }

    @Override
    public void handleEvent(AbstractEvent event) {

        PORepository.getInstance().handleEvent(event);

        if(event instanceof POCreatedEvent){

            String key = Utils.createGUID();
            String content = Utils.parseObjectToJson(event);
            Message message = new Message("ARTICLE",0, "POCreatedEvent", key, content);
            StreamEmitter.getInstance().send(message);
        }
    }

    public static void main(String[] args) {

        new PurchaseOrderService();
    }


}