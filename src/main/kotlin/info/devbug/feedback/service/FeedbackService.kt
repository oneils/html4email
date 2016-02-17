package info.devbug.feedback.service

import info.devbug.feedback.repository.FeedbackDto

/**
 * @author Dzmitry Misiuk
 */
interface FeedbackService {

    fun send(feedback: FeedbackDto): FeedbackDto

    fun getAll(): List<FeedbackDto>

    fun getUnread(): List<FeedbackDto>

    fun getFeedback(id: String): FeedbackDto
}