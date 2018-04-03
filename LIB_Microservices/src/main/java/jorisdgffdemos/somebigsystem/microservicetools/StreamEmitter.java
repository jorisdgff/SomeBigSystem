package jorisdgffdemos.somebigsystem.microservicetools;

import jorisdgffdemos.somebigsystem.cqrskit.CQRSUtils;
import jorisdgffdemos.somebigsystem.crosscuttingconcerns.Utils;
import jorisdgffdemos.somebigsystem.domain.DomainEnums;
import jorisdgffdemos.somebigsystem.domain.DomainUtils;
import jorisdgffdemos.somebigsystem.shardingkit.ShardingRule;
import jorisdgffdemos.somebigsystem.shardingkit.ShardingRules;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.lang.reflect.AnnotatedElement;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class StreamEmitter {

    private Producer producer;
    private static StreamEmitter instance;

    private StreamEmitter() {

        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "192.168.178.20:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(kafkaProps);
    }

    public void send(Message message) {

        try {

            String json = Utils.parseObjectToJson(message);
            ProducerRecord<String, String> record = new ProducerRecord<>(message.topic, message.parition, "", json);
            producer.send(record).get();
        } catch (InterruptedException ex) {

            System.err.println(ex);
        } catch (ExecutionException ex) {

            System.err.println(ex);
        }
    }

    /*public void send(Object objectToSend){


    }*/

    private String getPartitionKey(Object communicationObject) {

        DomainEnums.Type typeOfObject = DomainUtils.getDomainTypeFromObject(communicationObject);
        String keyOfObject = DomainUtils.getDomainKey(communicationObject, typeOfObject);
        String partitionKey = lookupPartitionKey(typeOfObject, keyOfObject);

        if (!partitionKey.equals("")) {

            return partitionKey;
        } else {

            ShardingRule rule = ShardingRules.getInstance().getRule(typeOfObject);
            String keyForRule = DomainUtils.getDomainKey(communicationObject, rule.key);

            switch (rule.usage) {

                case USEASKEY:
                    return keyForRule;

                case USEITSKEY:
                    return lookupPartitionKey(rule.key, keyForRule);

                default:
                    return null;
            }
        }
    }

    private String lookupPartitionKey(DomainEnums.Type type, String key) {

        //TODO Lookup from database
        return "";
    }

    public static StreamEmitter getInstance() {

        if (instance == null) {

            instance = new StreamEmitter();
        }

        return instance;
    }
}