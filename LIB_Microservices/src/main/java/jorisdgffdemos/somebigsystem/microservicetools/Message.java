package jorisdgffdemos.somebigsystem.microservicetools;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import jorisdgffdemos.somebigsystem.cqrskit.CQRSUtils;
import jorisdgffdemos.somebigsystem.crosscuttingconcerns.Utils;
import jorisdgffdemos.somebigsystem.domain.DomainEnums;
import jorisdgffdemos.somebigsystem.domain.DomainUtils;

public class Message {

    @JsonIgnore
    public final String topic;
    @JsonIgnore
    public final int parition;
    public final String type;
    public final String key;
    public final Object content;

    @JsonCreator
    public Message(@JsonProperty("type") String type,
                   @JsonProperty("key") String key,
                   @JsonProperty("content") Object content){

        this.topic = "";
        this.parition = 0;
        this.type = type;
        this.key = key;
        this.content = content;
    }

    public Message(String topic, int parition, String type, String key, String content){

        this.topic = topic;
        this.parition = parition;
        this.type = type;
        this.key = key;
        this.content = content;
    }
}