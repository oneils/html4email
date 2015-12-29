package info.devbug.article

import org.springframework.beans.factory.annotation.Autowired
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

    fun findArticleById(id: Int): ArticleDto {
        return articleRepository.findOne(id)
    }

    fun findAll(): List<ArticleDto> {
        return articleRepository.findAll()
    }

    fun save(article: ArticleDto) {
        articleRepository.save(article)
    }

    fun delete(id: Int) {
        articleRepository.delete(id)
    }
}