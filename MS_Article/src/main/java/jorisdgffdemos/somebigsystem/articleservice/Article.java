package jorisdgffdemos.somebigsystem.articleservice;


import jorisdgffdemos.somebigsystem.communicationonbjects.article.commands.*;
import jorisdgffdemos.somebigsystem.communicationonbjects.article.events.*;
import jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.events.POCreatedEvent;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractCommand;
import jorisdgffdemos.somebigsystem.cqrskit.Acknowledgement;
import jorisdgffdemos.somebigsystem.domain.types.ArticleContract;
import jorisdgffdemos.somebigsystem.microservicetools.AbstractAggregate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jorisdgffdemos.somebigsystem.microservicetools.Message;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article extends AbstractAggregate implements ArticleContract {

    private String articleCode;
    private String description;
    private boolean isHistorical;

    public Article(){

        this.articleCode = "";
        this.description = "";
        this.isHistorical = false;
    }

    public Article(String articleCode, String description, boolean isHistorical){

        this.articleCode = articleCode;
        this.description = description;
        this.isHistorical = isHistorical;
    }

    public void handle(Object cmd){

        if(cmd instanceof ArticleCreateCommand){

            handle((ArticleCreateCommand) cmd);
        }else if(cmd instanceof ArticleChangeDescriptionCommand){

            handle((ArticleChangeDescriptionCommand) cmd);
        }else if(cmd instanceof ArticleMakeHistoricCommand){

            handle((ArticleMakeHistoricCommand) cmd);
        }else if(cmd instanceof POCreatedEvent){

            handle((POCreatedEvent) cmd);
        }
    }

    public void handle(ArticleCreateCommand command){

        if(command.articleCode.length() != 6){

            //return "FOUT";
        }else{

            this.articleCode = command.articleCode;
            this.description = command.description;
            this.isHistorical = false;
            emitEvent(new ArticleCreatedEvent(this));
        }
    }

    public void handle(ArticleChangeDescriptionCommand command){

        this.description = command.newDescription;
        emitEvent(new ArticleDescriptionChangedEvent(command.articleCode, command.newDescription));
    }

    public void handle(ArticleMakeHistoricCommand command){

        this.isHistorical = true;
        emitEvent(new ArticleMadeHistoricalEvent(command.articleCode));
    }

    public void handle(POCreatedEvent event){

        if(!this.isHistorical){

            emitEvent(new Acknowledgement(event.guid, "ARTICLEISHISTORICAL"));
        }else{

            emitEvent(new Acknowledgement(event.guid, "OK"));
        }

        Message message = new Message("PURCHASEORDER", 0, );
    }

    public String getArticleCode() {

        return articleCode;
    }

    public String getDescription() {

        return description;
    }

    public boolean isHistorical() {

        return isHistorical;
    }
}