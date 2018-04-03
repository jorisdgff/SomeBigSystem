package jorisdgffdemos.somebigsystem.crosscuttingconcerns;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoLogger {

    private static MongoLogger instance;
    private MongoCollection<Document> collection;

    private MongoLogger(){

        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("Logs");
        collection = database.getCollection("logs");
    }

    public void write(String message){

        Document doc = new Document();
        doc.append("message", message);
        collection.insertOne(doc);
    }

    public static MongoLogger getInstance() {

        if(instance == null){

            instance = new MongoLogger();
        }

        return instance;
    }
}
