package jorisdgffdemos.somebigsystem.ms_purchaseorder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.commands.*;
import jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.events.*;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractCommand;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;
import jorisdgffdemos.somebigsystem.domain.types.PurchaseOrderContract;
import jorisdgffdemos.somebigsystem.microservicetools.AbstractAggregate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseOrder extends AbstractAggregate implements PurchaseOrderContract {

    private String purchaseOrderCode;
    private String articleCode;
    private String buyerCode;
    private int amount;

    public PurchaseOrder(){

        purchaseOrderCode = "";
        articleCode = "";
        amount = 0;
    }

    public String getPurchaseOrderCode() {
        return purchaseOrderCode;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public int getAmount() {
        return amount;
    }

    public void handle(AbstractCommand cmd){

        if(cmd instanceof POCreateCommand){

            handle((POCreateCommand) cmd);
        }else if(cmd instanceof POIncreaseAmountCommand){

            handle((POIncreaseAmountCommand) cmd);
        }
    }

    private void handle(POCreateCommand command){

        this.purchaseOrderCode = command.purchaseOrderCode;
        this.articleCode = command.articleCode;
        this.buyerCode = command.buyerCode;
        this.amount = command.amount;
        emitEvent(new POCreatedEvent(purchaseOrderCode, articleCode, buyerCode, amount));
    }

    private void handle(POIncreaseAmountCommand command){

        this.amount = this.amount + command.amountToIncrease;
        emitEvent(new POAmountIncreasedEvent(purchaseOrderCode, command.amountToIncrease, this.amount));
    }

    public void projectEvent(AbstractEvent abstractEvent){

        if(abstractEvent instanceof POCreatedEvent){

            POCreatedEvent event = (POCreatedEvent) abstractEvent;
            this.purchaseOrderCode = event.purchaseOrderCode;
            this.articleCode = event.articleCode;
            this.amount = event.amount;
        }else if(abstractEvent instanceof POAmountIncreasedEvent){

            POAmountIncreasedEvent event = (POAmountIncreasedEvent) abstractEvent;
            this.amount = this.amount + event.increasedAmount;
        }
    }
}
