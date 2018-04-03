package jorisdgffdemos.somebigsystem.domain;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DomainKeyReference {

    public DomainEnums.Type value();
}