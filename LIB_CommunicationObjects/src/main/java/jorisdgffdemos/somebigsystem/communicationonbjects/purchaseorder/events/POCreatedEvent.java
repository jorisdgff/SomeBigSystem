package jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;
import jorisdgffdemos.somebigsystem.cqrskit.CommunicationObject;
import jorisdgffdemos.somebigsystem.domain.DomainEnums;
import jorisdgffdemos.somebigsystem.domain.DomainTypeReference;

@JsonIgnoreProperties(ignoreUnknown = true)
@DomainTypeReference(DomainEnums.Type.PURCHASEORDER)
@CommunicationObject("CREATED")
public class POCreatedEvent extends AbstractEvent{

    public final String purchaseOrderCode;
    public final String articleCode;
    public final String buyerCode;
    public final int amount;

    public POCreatedEvent(@JsonProperty("purchaseOrderCode") String purchaseOrderCode,
                          @JsonProperty("articleCode") String articleCode,
                          @JsonProperty("buyerCode") String buyerCode,
                          @JsonProperty("amount") int amount){

        super(purchaseOrderCode);
        this.purchaseOrderCode = purchaseOrderCode;
        this.articleCode = articleCode;
        this.buyerCode = buyerCode;
        this.amount = amount;
    }
}