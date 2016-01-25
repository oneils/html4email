package info.devbug.digest

import info.devbug.article.ArticleDto
import info.devbug.article.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author Aliaksei Bahdanau
 */
@RestController
@RequestMapping(value = "/v1/digests")
class DigestResource {
    private val digestService: DigestService
    private val articleService: ArticleService

    @Autowired constructor(digestService: DigestService, articleService: ArticleService) {
        this.digestService = digestService
        this.articleService = articleService
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun digests(): ResponseEntity<List<DigestDto>> {
        val digests = digestService.findAll()
        return ResponseEntity(digests, HttpStatus.OK)
    }

    @RequestMapping(value = "/{digestId}/articles", method = arrayOf(RequestMethod.GET))
    fun findArticlesByDigestId(@PathVariable("digestId") digestID: Int): ResponseEntity<List<ArticleDto>> {
        val articles: List<ArticleDto> = articleService.findArticles(digestID)
        return ResponseEntity(articles, HttpStatus.OK)
    }
}