package jorisdgffdemos.somebigsystem.ms_purchaseorder;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jorisdgffdemos.somebigsystem.communicationonbjects.article.events.ArticleInvalidOrderedEvent;
import jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.events.POAmountIncreasedEvent;
import jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.events.POCreatedEvent;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;
import jorisdgffdemos.somebigsystem.cqrskit.CQRSUtils;
import jorisdgffdemos.somebigsystem.crosscuttingconcerns.Utils;
import jorisdgffdemos.somebigsystem.microservicetools.AbstractRepository;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class PORepository extends AbstractRepository {

    private static PORepository instance;
    private MongoDatabase database;

    private PORepository(){

        MongoClient mongoClient = new MongoClient();
        database = mongoClient.getDatabase("purchaseordercommandservice");
    }

    public static PORepository getInstance() {

        if(instance == null){

            instance = new PORepository();
        }

        return instance;
    }

    public PurchaseOrder getPurchaseOrder(String code){

        PurchaseOrder purchaseOrder = new PurchaseOrder();


        MongoCollection<Document> collection = database.getCollection("po_" + code + "");

        for (Document doc : collection.find()) {

            String eventType = doc.getString("type");
            String contentAsJson = ((Document) doc.get("content")).toJson();

            if(eventType.equals("CREATED")){

                purchaseOrder.projectEvent(Utils.parseJsonToObject(contentAsJson, POCreatedEvent.class));
            }else if(eventType.equals("AMOUNTINCREASED")){

                purchaseOrder.projectEvent(Utils.parseJsonToObject(contentAsJson, POAmountIncreasedEvent.class));
            }
        }

        return purchaseOrder;
    }

    public void handleEvent(AbstractEvent event) {

        boolean isRevertNeeded = event instanceof ArticleInvalidOrderedEvent;

        String purchaseOrderCode =  event.code;

        if(!isRevertNeeded){

            MongoCollection<Document> collection = database.getCollection("po_" + purchaseOrderCode);

            Document document = new Document("guid", event.guid)
                    .append("type", CQRSUtils.getCommunicationObjectTypeFromObject(event))
                    .append("content", Document.parse(Utils.parseObjectToJson(event)));

            collection.insertOne(document);
        }else{

            ArticleInvalidOrderedEvent e = (ArticleInvalidOrderedEvent) event;
            MongoCollection<Document> collection = database.getCollection("po_" + purchaseOrderCode);


            collection.deleteOne(eq("guid", e.originalEventGuid));
        }
    }
}