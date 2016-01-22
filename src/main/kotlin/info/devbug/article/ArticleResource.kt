package info.devbug.article

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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
    @ResponseBody
    fun articles(): List<ArticleDto> {
        return articleService.findAll()
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST), consumes = arrayOf("application/json"))
    fun save(@RequestBody article: ArticleDto): ArticleDto {
        return articleService.save(article)
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable("id") id: Int): String {
        articleService.delete(id)
        return "Ok"
    }
}