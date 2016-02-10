package info.devbug.article;

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

/**
 * @author Aliaksei Bahdanau.
 */

@Document(collection = "articles")
class ArticleDto() {
    constructor(title: String, url: String, description: String) : this() {
        this.title = title
        this.url = url
        this.description = description
    }

    @Id
    var id: String? = null
    var title: String = ""
    var description: String = ""
    var url: String = ""
    var createdDate: Date = Date()

}