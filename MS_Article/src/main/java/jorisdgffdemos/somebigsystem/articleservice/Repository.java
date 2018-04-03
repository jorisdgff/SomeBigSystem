package jorisdgffdemos.somebigsystem.articleservice;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jorisdgffdemos.somebigsystem.communicationonbjects.article.events.ArticleCreatedEvent;
import jorisdgffdemos.somebigsystem.communicationonbjects.article.events.ArticleDescriptionChangedEvent;
import jorisdgffdemos.somebigsystem.communicationonbjects.article.events.ArticleMadeHistoricalEvent;
import jorisdgffdemos.somebigsystem.crosscuttingconcerns.Utils;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;
import jorisdgffdemos.somebigsystem.microservicetools.AbstractRepository;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class Repository extends AbstractRepository {

    private static Repository instance;

    private MongoCollection<Document> collection;

    private Repository(){

        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("articleservice");
        collection = database.getCollection("articles");
    }

    public Article getArticle(String code){

        Document doc = collection.find(eq("articleCode", code)).first();
        Article article = null;

        if(doc != null){

            String json = doc.toJson();
            article = Utils.parseJsonToObject(json, Article.class);
        }else{

            article = new Article();
        }

        return article;
    }

    public void handleEvent(AbstractEvent event) {

        if(event instanceof ArticleCreatedEvent){

            saveNewArticle((ArticleCreatedEvent) event);
        }else if(event instanceof ArticleDescriptionChangedEvent){

            updateArticleDescription((ArticleDescriptionChangedEvent) event);
        }else if(event instanceof ArticleMadeHistoricalEvent){

            markArticleAsHistorical((ArticleMadeHistoricalEvent) event);
        }
    }

    private void saveNewArticle(ArticleCreatedEvent event){

        Document doc = Document.parse(Utils.parseObjectToJson(event.article));
        collection.insertOne(doc);
    }

    private void updateArticleDescription(ArticleDescriptionChangedEvent event){

        collection.updateOne(eq("articleCode", event.articleCode), set("description", event.newDescription));
    }

    private void markArticleAsHistorical(ArticleMadeHistoricalEvent event){

        collection.updateOne(eq("articleCode", event.articleCode), set("historical", true));
    }

    public static Repository getInstance() {

        if(instance == null){

            instance = new Repository();
        }

        return instance;
    }
}