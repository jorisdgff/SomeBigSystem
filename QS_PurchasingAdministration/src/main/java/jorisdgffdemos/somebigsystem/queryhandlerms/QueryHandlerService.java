package jorisdgffdemos.somebigsystem.queryhandlerms;

import jorisdgffdemos.somebigsystem.microservicetools.StreamReader;
import jorisdgffdemos.somebigsystem.microservicetools.StreamReaderCallback;

import java.util.HashMap;

public class QueryHandlerService implements StreamReaderCallback{

    private HashMap<String, Class> hashMap;

    private QueryHandlerService(){

        StreamReader.getInstance("queryservice", "QUERY", this);
    }

    public static void main(String[] args){

        new QueryHandlerService();
    }

    @Override
    public String eventReceived(String key, String value) {

        if(key != null && value != null){

            System.out.println(key);
            System.out.println(value);
            System.exit(0);
        }

        return "";
    }
}

