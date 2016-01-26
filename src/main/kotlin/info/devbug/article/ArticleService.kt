package info.devbug.article

import info.devbug.dto.TopicDto
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

    fun save(article: ArticleDto): ArticleDto {
        return articleRepository.save(article)
    }

    fun delete(id: Int) {
        articleRepository.delete(id)
    }

    fun findArticles(digestID: Int): List<ArticleDto> {
        return articleRepository.findArticlesByDigestId(digestID)
    }

    fun topics(): List<TopicDto> {
        return articleRepository.articles();
    }
}