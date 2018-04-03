package jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractCommand;
import jorisdgffdemos.somebigsystem.cqrskit.CommunicationObject;
import jorisdgffdemos.somebigsystem.domain.DomainEnums;
import jorisdgffdemos.somebigsystem.domain.DomainKeyReference;
import jorisdgffdemos.somebigsystem.domain.DomainTypeReference;

@DomainTypeReference(DomainEnums.Type.PURCHASEORDER)
@CommunicationObject("POCreateCommand")
public class POCreateCommand extends AbstractCommand{

    @DomainKeyReference(DomainEnums.Type.PURCHASEORDER)
    public final String purchaseOrderCode;
    @DomainKeyReference(DomainEnums.Type.ARTICLE)
    public final String articleCode;
    public final String buyerCode;
    public final int amount;

    @JsonCreator
    public POCreateCommand(@JsonProperty("purchaseOrderCode") String purchaseOrderCode,
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