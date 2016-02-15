package info.devbug.feedback

import info.devbug.feedback.repository.FeedbackDto
import info.devbug.feedback.service.FeedbackService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author Dzmitry Misiuk
 */
@RestController
@RequestMapping("/v1/feedbacks")
class FeedbackResource {
    private val feedbackService: FeedbackService

    @Autowired constructor(feedbackService: FeedbackService) {
        this.feedbackService = feedbackService
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST), consumes = arrayOf("application/json"))
    fun send(@RequestBody feedback: FeedbackDto): ResponseEntity<FeedbackDto> {
        val feedback = feedbackService.send(feedback)
        return ResponseEntity(feedback, HttpStatus.OK)
    }
}