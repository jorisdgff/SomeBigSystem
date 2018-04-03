package jorisdgffdemos.somebigsystem.communicationonbjects.article.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractCommand;
import jorisdgffdemos.somebigsystem.cqrskit.CommunicationObject;
import jorisdgffdemos.somebigsystem.domain.DomainEnums;
import jorisdgffdemos.somebigsystem.domain.DomainKeyReference;
import jorisdgffdemos.somebigsystem.domain.DomainTypeReference;

@DomainTypeReference(DomainEnums.Type.ARTICLE)
@CommunicationObject("ArticleMakeHistoricCommand")
public class ArticleMakeHistoricCommand extends AbstractCommand{

    @DomainKeyReference(DomainEnums.Type.ARTICLE)
    public final String articleCode;

    @JsonCreator
    public ArticleMakeHistoricCommand(@JsonProperty("articleCode") String articleCode){

        super(articleCode);
        this.articleCode = articleCode;
    }
}
