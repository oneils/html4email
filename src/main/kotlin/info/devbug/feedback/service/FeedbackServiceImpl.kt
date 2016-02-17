package info.devbug.feedback.service

import info.devbug.feedback.repository.FeedbackDto
import info.devbug.feedback.repository.FeedbackRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Dzmitry Misiuk
 */
@Service
class FeedbackServiceImpl @Autowired constructor(private val feedbackRepository: FeedbackRepository) : FeedbackService {

    override fun send(feedback: FeedbackDto): FeedbackDto {
        return feedbackRepository.save(feedback)
    }
}