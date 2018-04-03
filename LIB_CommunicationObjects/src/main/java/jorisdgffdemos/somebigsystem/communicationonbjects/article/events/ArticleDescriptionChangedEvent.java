package jorisdgffdemos.somebigsystem.communicationonbjects.article.events;

import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;

public class ArticleDescriptionChangedEvent extends AbstractEvent {

    public final String articleCode;
    public final String newDescription;

    public ArticleDescriptionChangedEvent(String articleCode, String newDescription){

        super(articleCode);
        this.articleCode = articleCode;
        this.newDescription = newDescription;
    }
}