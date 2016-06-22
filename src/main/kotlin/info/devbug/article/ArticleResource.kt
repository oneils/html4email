package info.devbug.article

import info.devbug.api.RestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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
open class ArticleResource {
    private val articleService: ArticleService

    @Autowired constructor(articleService: ArticleService) {
        this.articleService = articleService
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun articles(@RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
                 @RequestParam(value = "size", required = false, defaultValue = "10") size: Int):
            ResponseEntity<Page<ArticleDto>> {
        val pageRequest: PageRequest = PageRequest(page, size, Sort.Direction.DESC, "creationDate");
        val articles = articleService.findAll(pageRequest)
        return ResponseEntity(articles, HttpStatus.OK)
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"))
    fun save(@RequestBody article: ArticleDto): ResponseEntity<ArticleDto> {
        val savedArticle = articleService.save(article)

        val responseHeaders = HttpHeaders()
        val newPollUri: URI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedArticle.id)
                .toUri();
        responseHeaders.location = newPollUri;

        return ResponseEntity(savedArticle, responseHeaders, HttpStatus.CREATED)
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable("id") id: String): ResponseEntity<ArticleDto> {
        articleService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    fun findById(@PathVariable("id") id: String): ResponseEntity<ArticleDto> {
        val article = articleService.findArticleById(id)
        return ResponseEntity(article, HttpStatus.OK)
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.PUT))
    fun updateArticle(@RequestBody article: ArticleDto, @PathVariable("id") id: String): ResponseEntity<ArticleDto> {
        return ResponseEntity(articleService.save(article), HttpStatus.OK)
    }
}