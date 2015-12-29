package info.devbug.article;

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Aliaksei Bahdanau.
 */
@Repository
interface ArticleRepository : CrudRepository<ArticleDto, Int> {

    fun findByTitle(title: String): List<ArticleDto>

    override fun findAll(): List<ArticleDto>

}