package info.idgst.topic;

import com.google.common.base.Objects;
import info.idgst.article.Article;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author Aliaksei Bahdanau
 */
public class Topic implements Serializable {

    private String topic;
    private int orderPriority;
    private List<Article> articles = Collections.emptyList();

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(int orderPriority) {
        this.orderPriority = orderPriority;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic1 = (Topic) o;
        return orderPriority == topic1.orderPriority && Objects.equal(topic, topic1.topic) &&
               Objects.equal(articles, topic1.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(topic, orderPriority, articles);
    }
}
