package jorisdgffdemos.somebigsystem.communicationonbjects.article.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractCommand;
import jorisdgffdemos.somebigsystem.cqrskit.CommunicationObject;
import jorisdgffdemos.somebigsystem.domain.DomainEnums;
import jorisdgffdemos.somebigsystem.domain.DomainKeyReference;
import jorisdgffdemos.somebigsystem.domain.DomainTypeReference;

@DomainTypeReference(DomainEnums.Type.ARTICLE)
@CommunicationObject("ArticleChangeDescriptionCommand")
public class ArticleChangeDescriptionCommand extends AbstractCommand{

    @DomainKeyReference(DomainEnums.Type.ARTICLE)
    public final String articleCode;
    public final String newDescription;

    @JsonCreator
    public ArticleChangeDescriptionCommand(@JsonProperty("articleCode") String articleCode,
                                           @JsonProperty("newDescription") String newDescription){

        super(articleCode);

        this.articleCode = articleCode;
        this.newDescription = newDescription;
    }
}