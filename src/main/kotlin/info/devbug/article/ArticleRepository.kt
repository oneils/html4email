package info.devbug.article;

import info.devbug.topic.TopicDto
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * @author Aliaksei Bahdanau.
 */
@Repository
interface ArticleRepository : CrudRepository<ArticleDto, Int> {

    fun findByTitle(title: String): List<ArticleDto>

    override fun findAll(): List<ArticleDto>

    @Query("SELECT a " +
            "FROM ArticleDto as a,  " +
            "DigestTopicArticleDto as dta " +
            "WHERE dta.digestId = :digestID " +
            "AND a.id = dta.articleId")
    fun findArticlesByDigestId(@Param("digestID") digestID: Int): List<ArticleDto>

    @Query("SELECT topic FROM TopicDto as topic")
    fun articles(): List<TopicDto>

}