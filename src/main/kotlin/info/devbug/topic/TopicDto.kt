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
        this.topic = name
        this.orderPriority = orderPriority
    }

    @Id
    var id: String = ""

    var topic: String = ""

    var orderPriority: Int = 0

    var articles: List<ArticleDto> = emptyList()

}