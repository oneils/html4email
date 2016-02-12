package info.devbug.article;

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

/**
 * @author Aliaksei Bahdanau.
 */
@Repository
interface ArticleRepository : PagingAndSortingRepository<ArticleDto, String> {

    fun findByTitle(title: String): List<ArticleDto>

    override fun findAll(): List<ArticleDto>
}