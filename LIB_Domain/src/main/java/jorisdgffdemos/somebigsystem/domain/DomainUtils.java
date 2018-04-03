package jorisdgffdemos.somebigsystem.domain;

import java.lang.reflect.Field;

public class DomainUtils {

    public static DomainEnums.Type getDomainTypeFromObject(Object object){

        DomainTypeReference ref = object.getClass().getAnnotation(DomainTypeReference.class);

        if(ref != null){

            return ref.value();
        }

        return null;
    }

    public static String getDomainKey(Object object, DomainEnums.Type wantedType){

        for(Field field : object.getClass().getFields()){

            DomainKeyReference ref = field.getAnnotation(DomainKeyReference.class);

            if(ref != null && ref.value() == wantedType){

                try{

                    return  (String) field.get(object);
                }catch (IllegalAccessException ex){

                    System.err.println(ex);
                }
            }
        }

        return null;
    }
}