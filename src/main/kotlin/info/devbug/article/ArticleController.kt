package info.devbug.article

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * @author Aliaksei Bahdanau.
 */
@Controller
class ArticleController {
    private val articleService: ArticleService

    @Autowired constructor(articleService: ArticleService) {
        this.articleService = articleService
    }

    @RequestMapping(value = "/v1/articles/{id}", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun article(@PathVariable("id") id: Int): ArticleDto? {
        return articleService.findArticleById(id)
    }

    @RequestMapping(value = "/v1/articles", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun articles(): List<ArticleDto> {
        return articleService.findAll()
    }

    @RequestMapping(value = "/articles")
    fun articlesView(model: Model): String {
        model.addAttribute("articles", articleService.findAll())
        return "articles"
    }

    @RequestMapping(value = "/articles/saveArticle", method = arrayOf(RequestMethod.POST))
    fun save(@RequestParam("title") title: String,
             @RequestParam("url") url: String,
             @RequestParam("description") description: String): String {
        val article = ArticleDto(title, url, description)
        article.description = description
        article.url = url
        article.title = title
        articleService.save(article)
        return "redirect:/articles"
    }

    // TODO: replace GET to POST
    @RequestMapping(value = "/articles/delete", method = arrayOf(RequestMethod.POST))
    fun delete(@RequestParam("id") id: Int): String {
        articleService.delete(id)
        return "redirect:/articles"
    }
}