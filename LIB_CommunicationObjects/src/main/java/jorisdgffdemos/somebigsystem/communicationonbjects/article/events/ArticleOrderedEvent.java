package jorisdgffdemos.somebigsystem.communicationonbjects.article.events;

import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;

public class ArticleOrderedEvent extends AbstractEvent {

    public final String articleCode;
    public final String purchaseOrderCode;
    public final String description;

    public ArticleOrderedEvent(String articleCode, String purchaseOrderNumer, String description){

        super(articleCode);
        this.articleCode = articleCode;
        this.purchaseOrderCode = purchaseOrderNumer;
        this.description = description;
    }
}