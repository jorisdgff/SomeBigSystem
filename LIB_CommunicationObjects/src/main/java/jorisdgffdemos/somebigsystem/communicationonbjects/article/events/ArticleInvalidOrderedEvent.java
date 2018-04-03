package jorisdgffdemos.somebigsystem.communicationonbjects.article.events;


import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;
import jorisdgffdemos.somebigsystem.cqrskit.CommunicationObject;

@CommunicationObject("ArticleInvalidOrderedEvent")
public class ArticleInvalidOrderedEvent extends AbstractEvent {

    public String purchaseOrderCode;
    public String originalEventGuid;

    public ArticleInvalidOrderedEvent(String purchaseOrderCode, String originalEventGuid){

        super(purchaseOrderCode);
        this.purchaseOrderCode = purchaseOrderCode;
        this.originalEventGuid = originalEventGuid;
    }
}