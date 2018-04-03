package jorisdgffdemos.somebigsystem.commandbus;

import jorisdgffdemos.somebigsystem.communicationonbjects.article.commands.ArticleChangeDescriptionCommand;
import jorisdgffdemos.somebigsystem.communicationonbjects.article.commands.ArticleCreateCommand;
import jorisdgffdemos.somebigsystem.communicationonbjects.article.commands.ArticleMakeHistoricCommand;
import jorisdgffdemos.somebigsystem.microservicetools.Message;
import jorisdgffdemos.somebigsystem.microservicetools.MessageFactory;
import jorisdgffdemos.somebigsystem.microservicetools.StreamEmitter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleCommandService {

    @RequestMapping("/create")
    public static void create(@RequestParam("vendorCode") String vendorCode,
                                @RequestParam("articleCode") String articleCode,
                                @RequestParam("description") String description){

        ArticleCreateCommand command = new ArticleCreateCommand(vendorCode, articleCode, description);
        StreamEmitter.getInstance().send(MessageFactory.getInstance().create(command));
    }

    @RequestMapping("/changeDescription")
    public static void changeDescription(@RequestParam("articleCode") String articleCode,
                                         @RequestParam("newDescription") String newDescription){

        ArticleChangeDescriptionCommand command = new ArticleChangeDescriptionCommand(articleCode, newDescription);
        StreamEmitter.getInstance().send(MessageFactory.getInstance().create(command));
    }

    @RequestMapping("/changeDescription")
    public static void makeHistorical(@RequestParam("articleCode") String articleCode){

        ArticleMakeHistoricCommand command = new ArticleMakeHistoricCommand(articleCode);
        StreamEmitter.getInstance().send(MessageFactory.getInstance().create(command));
    }
}