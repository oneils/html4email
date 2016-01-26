package info.devbug.dto

import javax.persistence.*

/**
 * @author Aliaksei Bahdanau
 */

@Entity
@Table(name = "digest_topic_article")
class DigestTopicArticleDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "digest_topic_article_dta_id_seq")
    @SequenceGenerator(name = "digest_topic_article_dta_id_seq", sequenceName = "digest_topic_article_dta_id_seq", allocationSize = 1)
    @Column(name = "dta_id")
    var id: Int = 0

    @Column(name = "digest_id")
    var digestId: Int = 0

    @Column(name = "topic_id")
    var topicId: Int = 0

    @Column(name = "article_id")
    var articleId: Int = 0
}