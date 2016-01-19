package info.devbug.article

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * @author Aliaksei Bahdanau.
 */
@RestController
@RequestMapping(value = "/v1/articles/")
class ArticleResource {
    private val articleService: ArticleService

    @Autowired constructor(articleService: ArticleService) {
        this.articleService = articleService
    }

//    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
//    @ResponseBody
//    fun article(@PathVariable("id") id: Int): ArticleDto? {
//        return articleService.findArticleById(id)
//    }

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun articles(): List<ArticleDto> {
        return articleService.findAll()
    }

//    @RequestMapping(value = "/articles")
//    fun articlesView(model: Model): String {
//        model.addAttribute("articles", articleService.findAll())
//        return "articles"
//    }

//    @RequestMapping(value = "/v1/articles/", method = arrayOf(RequestMethod.POST))
//    fun save(@RequestParam("title") title: String,
//             @RequestParam("url") url: String,
//             @RequestParam("description") description: String): String {
//        val article = ArticleDto(title, url, description)
//        article.description = description
//        article.url = url
//        article.title = title
//        articleService.save(article)
//        return "redirect:/articles"
//    }

    @RequestMapping(method = arrayOf(RequestMethod.DELETE))
    fun delete(@RequestParam("id") id: Int): String {
        articleService.delete(id)
        return "Ok"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST)/*, consumes = arrayOf("application/json")*/)
    @ResponseBody
    fun save(): String {
        val article = ArticleDto("!", "!", "!")
        articleService.save(article)
        return "Ok"
    }

    @RequestMapping(value = "/v1/test/", method = arrayOf(RequestMethod.POST))
    @ResponseBody
    fun test(): String {
        val article = ArticleDto("!", "!", "!")
        articleService.save(article)
        return "Ok"
    }
}