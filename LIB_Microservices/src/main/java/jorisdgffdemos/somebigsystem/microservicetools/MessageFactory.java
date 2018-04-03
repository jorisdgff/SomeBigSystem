package jorisdgffdemos.somebigsystem.microservicetools;

import jorisdgffdemos.somebigsystem.cqrskit.CQRSUtils;
import jorisdgffdemos.somebigsystem.crosscuttingconcerns.Utils;
import jorisdgffdemos.somebigsystem.domain.DomainUtils;

public class MessageFactory {

    private static MessageFactory instance;

    private MessageFactory(){

    }

    public Message create(Object objectToSend){

        /*String partitionKey = this.topic + "_" + getPartitionKey(content);
        int partitionNumber = 0 CQRSUtils.createIntFromString(partitionKey);*/

        String topic = DomainUtils.getDomainTypeFromObject(objectToSend).toString();
        int parition = 0;
        String type = CQRSUtils.getCommunicationObjectTypeFromObject(objectToSend);
        String key = Utils.createGUID();
        String content = Utils.parseObjectToJson(objectToSend);

        return new Message(topic, parition, type, key, content);
    }

    public static MessageFactory getInstance() {

        if(instance == null){

            instance = new MessageFactory();
        }

        return instance;
    }
}