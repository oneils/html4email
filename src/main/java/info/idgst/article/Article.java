package info.idgst.article;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents Article Entity which is mapped to articles collection.
 *
 * @author Aliaksei Bahdanau
 */
public class Article implements Serializable {

    private String title;
    private String description;
    private String url;
    private Date createdDt;

    public Article(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public Article() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return  Objects.equal(title, article.title) &&
                Objects.equal(description, article.description) &&
                Objects.equal(url, article.url) &&
                Objects.equal(createdDt, article.createdDt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title, description, url, createdDt);
    }
}
