package jorisdgffdemos.somebigsystem.articleservice;

import jorisdgffdemos.somebigsystem.communicationonbjects.article.commands.*;
import jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.events.POCreatedEvent;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractCommand;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;
import jorisdgffdemos.somebigsystem.crosscuttingconcerns.Utils;
import jorisdgffdemos.somebigsystem.microservicetools.*;

import java.util.HashMap;
import java.util.function.Function;

public class ArticleService extends AbstractService implements StreamReaderCallback{

    private HashMap<String, Class> hashMap;

    private ArticleService(){

        hashMap = new HashMap<>();
        //TODO naar config file verplaatsen
        hashMap.put("ArticleCreateCommand", ArticleCreateCommand.class);
        hashMap.put("ArticleChangeDescriptionCommand", ArticleChangeDescriptionCommand.class);
        hashMap.put("ArticleMakeHistoricCommand", ArticleMakeHistoricCommand.class);
        hashMap.put("POCreatedEvent", POCreatedEvent.class);

        StreamReader.getInstance("articleservice", "ARTICLE", this);
    }

    @Override
    public void eventReceived(Message message, String content) {

        AbstractCommand command = (AbstractCommand) Utils.parseJsonToObject(content, hashMap.get(message.type));
        Article article = Repository.getInstance().getArticle(command.code);
        article.setListener(this);
        article.handle(command);
    }

    @Override
    public void handleEvent(AbstractEvent event) {

        Repository.getInstance().handleEvent(event);
    }

    public static void main(String[] args){

        new ArticleService();
    }
}