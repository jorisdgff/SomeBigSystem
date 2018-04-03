package jorisdgffdemos.somebigsystem.cqrskit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jorisdgffdemos.somebigsystem.crosscuttingconcerns.Utils;

public abstract class AbstractCommand {

    @JsonIgnore
    public final String code;

    protected AbstractCommand(String code){

        this.code = code;
    }
}
