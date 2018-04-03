package jorisdgffdemos.somebigsystem.microservicetools;

import jorisdgffdemos.somebigsystem.crosscuttingconcerns.Utils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class StreamReader {

    private KafkaConsumer<String, String> consumer;
    private static StreamReader instance;

    private StreamReader(String groupId, String topic, StreamReaderCallback callback){

        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "192.168.178.20:9092");
        kafkaProps.put("group.id", groupId);
        kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<String, String>(kafkaProps);
        consumer.subscribe(Collections.singletonList(topic));

        //try{

             while (true){

                 ConsumerRecords<String, String> records = consumer.poll(100);

                 for(ConsumerRecord<String, String> record : records){

                    Message message = Utils.parseJsonToObject(record.value(), Message.class);
                    //String contentAsJson = Utils.parseObjectToJson(message.content);
                    callback.eventReceived(message, (String) message.content);
                 }
             }
        /*}finally {
            consumer.close();
        }*/
    }

    public static StreamReader getInstance(String groupid, String topic, StreamReaderCallback callback){

        if(instance == null){

            instance = new StreamReader(groupid, topic, callback);
        }

        return instance;
    }
}
