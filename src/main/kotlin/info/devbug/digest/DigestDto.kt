package info.devbug.digest

import info.devbug.topic.TopicDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

/**
 * @author Aliaksei Bahdanau
 */

@Document(collection = "digests")
class DigestDto() {

    constructor(title: String): this() {
        this.title = title
    }

    @Id
    var id: String? = null

    var title: String = ""

    var publishedDate: Date = Date()

    var createdDate: Date = Date()

    var topics: List<TopicDto> = emptyList()
}