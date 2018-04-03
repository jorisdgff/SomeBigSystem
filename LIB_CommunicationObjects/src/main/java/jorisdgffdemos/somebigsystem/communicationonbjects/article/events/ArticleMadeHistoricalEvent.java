package jorisdgffdemos.somebigsystem.communicationonbjects.article.events;

import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;

public class ArticleMadeHistoricalEvent extends AbstractEvent{

    public final String articleCode;

    public ArticleMadeHistoricalEvent(String articleCode){

        super(articleCode);
        this.articleCode = articleCode;
    }
}