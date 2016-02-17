package info.devbug.feedback

import info.devbug.feedback.repository.FeedbackDto
import info.devbug.feedback.service.FeedbackService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author Dzmitry Misiuk
 */
@RestController
@RequestMapping("/v1/feedbacks")
class FeedbackResource @Autowired constructor(private val feedbackService: FeedbackService) {

    @RequestMapping(method = arrayOf(RequestMethod.POST), consumes = arrayOf("application/json"))
    fun send(@RequestBody feedback: FeedbackDto): ResponseEntity<FeedbackDto> {
        val feedback = feedbackService.send(feedback)
        return ResponseEntity(feedback, HttpStatus.OK)
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun getAll(): ResponseEntity<List<FeedbackDto>> {
        val feedbacks = feedbackService.getAll()
        return ResponseEntity(feedbacks, HttpStatus.OK)
    }

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    fun getFeedback(@RequestParam("id") id: String): ResponseEntity<FeedbackDto> {
        val feedback = feedbackService.getFeedback(id)
        return ResponseEntity(feedback, HttpStatus.OK)
    }

    @RequestMapping("/unread", method = arrayOf(RequestMethod.GET))
    fun getUnread(): ResponseEntity<List<FeedbackDto>> {
        val unreadFeedbacks = feedbackService.getUnread()
        return ResponseEntity(unreadFeedbacks, HttpStatus.OK)
    }
}