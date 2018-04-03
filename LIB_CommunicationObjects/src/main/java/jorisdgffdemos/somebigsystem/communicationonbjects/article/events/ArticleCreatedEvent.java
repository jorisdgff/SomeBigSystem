package jorisdgffdemos.somebigsystem.communicationonbjects.article.events;

import jorisdgffdemos.somebigsystem.cqrskit.AbstractEvent;
import jorisdgffdemos.somebigsystem.domain.types.ArticleContract;

public class ArticleCreatedEvent extends AbstractEvent {

    public ArticleContract article;

    public ArticleCreatedEvent(ArticleContract article){

        super(article.getArticleCode());
        this.article = article;
    }
}