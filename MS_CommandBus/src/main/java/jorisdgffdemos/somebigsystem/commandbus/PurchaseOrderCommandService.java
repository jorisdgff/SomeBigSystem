package jorisdgffdemos.somebigsystem.commandbus;

import jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.commands.POCreateCommand;
import jorisdgffdemos.somebigsystem.communicationonbjects.purchaseorder.commands.POIncreaseAmountCommand;
import jorisdgffdemos.somebigsystem.microservicetools.Message;
import jorisdgffdemos.somebigsystem.microservicetools.MessageFactory;
import jorisdgffdemos.somebigsystem.microservicetools.StreamEmitter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchaseorder")
public class PurchaseOrderCommandService {

    @RequestMapping("/create")
    public static void create(@RequestParam("articleCode") String articleCode,
                              @RequestParam("purchaseOrderCode") String purchaseOrderCode,
                              @RequestParam("buyerCode") String buyerOrderCode,
                              @RequestParam("amountOrdered") int amountOrdered){

        POCreateCommand command = new POCreateCommand(purchaseOrderCode, articleCode, buyerOrderCode, amountOrdered);
        StreamEmitter.getInstance().send(MessageFactory.getInstance().create(command));
    }

    @RequestMapping("/increaseAmount")
    public static void increaseAmount(@RequestParam("purchaseOrderCode") String purchaseOrderCode,
                                      @RequestParam("amountToIncrease") int amountToIncreate){

        POIncreaseAmountCommand command = new POIncreaseAmountCommand(purchaseOrderCode, amountToIncreate);
        StreamEmitter.getInstance().send(MessageFactory.getInstance().create(command));
    }
}
