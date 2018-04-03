package jorisdgffdemos.somebigsystem.cqrskit;

public class CQRSUtils {

    public static String getCommunicationObjectTypeFromObject(Object object){

        CommunicationObject ref = object.getClass().getAnnotation(CommunicationObject.class);

        if(ref != null){

            return ref.value();
        }

        return null;
    }
}