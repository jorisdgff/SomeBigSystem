package jorisdgffdemos.somebigsystem.commandbus;

import jorisdgffdemos.somebigsystem.communicationonbjects.article.commands.ArticleCreateCommand;
import jorisdgffdemos.somebigsystem.crosscuttingconcerns.Utils;
import jorisdgffdemos.somebigsystem.microservicetools.Message;
import jorisdgffdemos.somebigsystem.microservicetools.StreamReader;
import jorisdgffdemos.somebigsystem.microservicetools.StreamReaderCallback;

public class CommandBusTestConsole {

    public static void main(String[] args){


        //ArticleCommandService.create("JGO", "223457", "Cool article 124");

        PurchaseOrderCommandService.create("123457", "666777", "KPM",5);
        //PurchaseOrderCommandService.increaseAmount("666777", 10);

        /*ArticleCreateCommand command = new ArticleCreateCommand("JGO", "123457", "Cool article 124");
        String jso = Utils.parseObjectToJson(command);



        ArticleCreateCommand c  = Utils.parseJsonToObject(jso, ArticleCreateCommand.class);*/

        //ArticleCommandService.create("JGO", "223457", "Cool article 124");
        /*ArticleCommandService.changeDescription("123457", "Next Cool Article");
        ArticleCommandService.makeHistorical("123457");*/


    }
}