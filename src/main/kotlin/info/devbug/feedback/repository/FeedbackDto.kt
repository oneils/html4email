package info.devbug.feedback.repository

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

/**
 * @author Dzmitry Misiuk
 */
@Document(collection = "feedbacks")
class FeedbackDto {

    @Id
    var id: String? = null

    var createdDate = Date()

    var title = ""

    var userName = ""

    var email = ""

    var read = false
}