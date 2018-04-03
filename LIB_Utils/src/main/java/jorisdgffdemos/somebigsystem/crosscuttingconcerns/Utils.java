package jorisdgffdemos.somebigsystem.crosscuttingconcerns;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.UUID;

public class Utils {

    public static String parseObjectToJson(Object object){

        try{

            ObjectMapper mapper = new ObjectMapper();

            String json =mapper.writeValueAsString(object);

            String noSlashes = json.replace("\\{", "{");

            return noSlashes;
        }catch (JsonProcessingException ex){

            MongoLogger.getInstance().write(ex.getMessage());
            System.err.println(ex);
            return null;
        }
    }

    public static <T> T parseJsonToObject(String json, Class<T> valueType){

        try {

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json,valueType);
        }catch (IOException ex){

            MongoLogger.getInstance().write(ex.getMessage());
            System.err.println(ex);
            return null;
        }
    }

    /*public static String getFieldFromJsonObject(Map<String object> map){

        try{

            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValueAsString()



            Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String,Object>>(){});
            return map.get(fieldname);
        }catch (IOException ex){

            System.err.println(ex);
            return null;
        }
    }*/

    public static int createIntFromString(String input){

        byte[] inputInBytes = input.getBytes();
        ByteBuffer wrapped = ByteBuffer.wrap(inputInBytes);
        return (int) wrapped.getShort();
    }

    public static String createGUID(){

        return UUID.randomUUID().toString();
    }
}