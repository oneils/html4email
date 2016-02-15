package info.devbug.feedback.repository

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

/**
 * @author Dzmitry Misiuk
 */
@Repository
interface FeedbackRepository : PagingAndSortingRepository<FeedbackDto, String> {
}