package info.devbug.article;

import com.fasterxml.jackson.annotation.JsonBackReference
import info.devbug.topic.TopicDto
import java.util.*
import javax.persistence.*

/**
 * @author Aliaksei Bahdanau.
 */

@Entity
@Table(name = "article")
class ArticleDto() {
    constructor(title: String, url: String, description: String) : this() {
        this.title = title
        this.url = url
        this.description = description
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_article_id_seq")
    @SequenceGenerator(name = "article_article_id_seq", sequenceName = "article_article_id_seq", allocationSize = 1)
    @Column(name = "article_id")
    var id: Int = 0
    var title: String = ""
    var description: String = ""
    var url: String = ""
    var createdDate: Date = Date()

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    @JsonBackReference
    var topic: TopicDto? = null
}