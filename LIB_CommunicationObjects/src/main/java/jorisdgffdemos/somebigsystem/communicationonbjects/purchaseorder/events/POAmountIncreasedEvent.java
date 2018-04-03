package jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;
import jorisdgffdemos.somebigsystem.cqrskit.CommunicationObject;

@JsonIgnoreProperties(ignoreUnknown = true)
@CommunicationObject("AMOUNTINCREASED")
public class POAmountIncreasedEvent extends AbstractEvent{

    public final String purchaseOrderCode;
    public final int increasedAmount;
    public final int newAmount;

    public POAmountIncreasedEvent(@JsonProperty("purchaseOrderNumber") String purchaseOrderNumber,
                                  @JsonProperty("increasedAmount") int increasedAmount,
                                  @JsonProperty("newAmount") int newAmount){

        super(purchaseOrderNumber);
        this.purchaseOrderCode = purchaseOrderNumber;
        this.increasedAmount = increasedAmount;
        this.newAmount = newAmount;
    }
}