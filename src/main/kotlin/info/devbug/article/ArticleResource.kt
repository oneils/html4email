package info.devbug.article

import info.devbug.topic.TopicDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

/**
 * @author Aliaksei Bahdanau.
 */
@RestController
@RequestMapping(value = "/v1/articles")
class ArticleResource {
    private val articleService: ArticleService

    @Autowired constructor(articleService: ArticleService) {
        this.articleService = articleService
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun articles(): ResponseEntity<List<ArticleDto>> {
        val articles = articleService.findAll()
        return ResponseEntity(articles, HttpStatus.OK)
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"))
    fun save(@RequestBody article: ArticleDto): ResponseEntity<ArticleDto> {
        val savedArticle = articleService.save(article)

        val responseHeaders: HttpHeaders = HttpHeaders()
        val newPollUri: URI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedArticle.id)
                .toUri();
        responseHeaders.location = newPollUri;

        return ResponseEntity(responseHeaders, HttpStatus.CREATED)
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable("id") id: Int): ResponseEntity<ArticleDto> {
        articleService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    fun findById(@PathVariable("id") id: Int): ResponseEntity<ArticleDto> {
        val article = articleService.findArticleById(id)
        return ResponseEntity(article, HttpStatus.OK)
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.PUT))
    fun updateArticle(@RequestBody article: ArticleDto, @PathVariable("id") id: Int): ResponseEntity<ArticleDto> {
        return ResponseEntity(articleService.save(article), HttpStatus.OK)
    }

    @RequestMapping(value = "/topics", method = arrayOf(RequestMethod.GET))
    fun topics(): ResponseEntity<List<TopicDto>> {
        return ResponseEntity(articleService.topics(), HttpStatus.OK)
    }
}