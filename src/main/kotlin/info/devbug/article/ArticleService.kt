package info.devbug.article

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

/**
 * @author Aliaksei Bahdanau.
 */
@Service
class ArticleService {
    private val articleRepository: ArticleRepository

    @Autowired constructor(articleRepository: ArticleRepository) {
        this.articleRepository = articleRepository
    }

    fun findArticleById(id: String): ArticleDto {
        return articleRepository.findOne(id)
    }

    fun findAll(pageRequest: PageRequest): Page<ArticleDto> {
        return articleRepository.findAll(pageRequest)
    }

    fun save(article: ArticleDto): ArticleDto {
        return articleRepository.save(article)
    }

    fun delete(id: String) {
        articleRepository.delete(id)
    }
}