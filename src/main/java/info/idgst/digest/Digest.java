package info.idgst.digest;

import com.google.common.base.Objects;
import info.idgst.topic.Topic;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Represents Digest Entity which is mapped to digests collection.
 *
 * @author Aliaksei Bahdanau
 */
@Document(collection = "digests")
public class Digest implements Serializable {
    @Id
    private String id;
    private String title;
    @Indexed
    private Date publishedDate;
    private Date createdDate;
    private List<Topic> topics;
    private String contributeTo;
    private String companyName;

    public Digest() {
    }

    public Digest(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Digest(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishedDate() {
        return publishedDate != null ? new Date(publishedDate.getTime()) : null;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = new Date(publishedDate.getTime());
    }

    public Date getCreatedDate() {
        return createdDate != null ? new Date(createdDate.getTime()) : null;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = new Date(createdDate.getTime());
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getContributeTo() {
        return contributeTo;
    }

    public void setContributeTo(String contributeTo) {
        this.contributeTo = contributeTo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Digest digest = (Digest) o;
        return Objects.equal(id, digest.id) && Objects.equal(title, digest.title) &&
               Objects.equal(publishedDate, digest.publishedDate) && Objects.equal(createdDate, digest.createdDate) &&
               Objects.equal(topics, digest.topics) && Objects.equal(contributeTo, digest.contributeTo) &&
               Objects.equal(companyName, digest.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, publishedDate, createdDate, topics, contributeTo, companyName);
    }
}
