package jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractCommand;
import jorisdgffdemos.somebigsystem.cqrskit.CommunicationObject;
import jorisdgffdemos.somebigsystem.domain.DomainEnums;
import jorisdgffdemos.somebigsystem.domain.DomainKeyReference;
import jorisdgffdemos.somebigsystem.domain.DomainTypeReference;

@DomainTypeReference(DomainEnums.Type.PURCHASEORDER)
@CommunicationObject("POIncreaseAmountCommand")
public class POIncreaseAmountCommand extends AbstractCommand{

    @DomainKeyReference(DomainEnums.Type.PURCHASEORDER)
    public String purchaseOrderCode;
    public int amountToIncrease;

    @JsonCreator
    public POIncreaseAmountCommand(@JsonProperty("purchaseOrderCode") String purchaseOrderCode,
                                   @JsonProperty("amountToIncrease") int amountToIncrease){

        super(purchaseOrderCode);

        this.purchaseOrderCode = purchaseOrderCode;
        this.amountToIncrease = amountToIncrease;
    }
}