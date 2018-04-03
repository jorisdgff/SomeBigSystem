package jorisdgffdemos.somebigsystem.cqrskit;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public abstract class AbstractEvent {

    @JsonIgnore
    public final String code;
    public final String guid;

    public AbstractEvent(String code){

        this.code = code;
        this.guid = UUID.randomUUID().toString();
    }
}