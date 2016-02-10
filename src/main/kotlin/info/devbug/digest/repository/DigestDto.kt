package info.devbug.digest.repository

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

    constructor(title: String, contributeTo: String, topics: List<TopicDto>): this() {
        this.title = title
        this.topics = topics
        this.contributeTo = contributeTo
    }

    @Id
    var id: String? = null

    var title: String = ""

    var publishedDate: Date = Date()

    var createdDate: Date = Date()

    var topics: List<TopicDto> = emptyList()

    var contributeTo: String = ""

    var companyName: String = ""
}