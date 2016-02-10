package info.devbug.topic

import info.devbug.article.ArticleDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Aliaksei Bahdanau
 */

@Document(collection = "topics")
class TopicDto() {
    constructor(name: String, orderPriority: Int) : this() {
        this.name = name
        this.orderPriority = orderPriority
    }

    @Id
    var id: String = ""

    var name: String = ""

    var orderPriority: Int = 0

    var articles: List<ArticleDto> = emptyList()

}