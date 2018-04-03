package jorisdgffdemos.somebigsystem.communicationonbjects.article.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jorisdgffdemos.somebigsystem.cqrskit.AbstractCommand;
import jorisdgffdemos.somebigsystem.cqrskit.CommunicationObject;

import jorisdgffdemos.somebigsystem.domain.DomainEnums;
import jorisdgffdemos.somebigsystem.domain.DomainKeyReference;
import jorisdgffdemos.somebigsystem.domain.DomainTypeReference;

@DomainTypeReference(DomainEnums.Type.ARTICLE)
@CommunicationObject("ArticleCreateCommand")
public class ArticleCreateCommand extends AbstractCommand{

    @DomainKeyReference(DomainEnums.Type.ARTICLE)
    public final String articleCode;
    @DomainKeyReference(DomainEnums.Type.VENDOR)
    public final String vendorCode;
    public final String description;

    @JsonCreator
    public ArticleCreateCommand(@JsonProperty("vendorCode") String vendorCode,
                                @JsonProperty("articleCode") String articleCode,
                                @JsonProperty("description") String description){

        super(articleCode);

        this.articleCode = articleCode;
        this.vendorCode = vendorCode;
        this.description = description;
    }
}