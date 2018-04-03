package jorisdgffdemos.somebigsystem.cqrskit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommunicationObject {

    public String value();
}
