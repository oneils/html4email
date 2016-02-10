package info.devbug.topic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Aliaksei Bahdanau
 */

@Service
class TopicService {
    private val topicRepository: TopicRepository

    @Autowired constructor(topicRepository: TopicRepository) {
        this.topicRepository = topicRepository
    }

    fun findAll(): List<TopicDto> {
        return topicRepository.findAll()
    }

    fun findById(topicId: Int): TopicDto {
        return topicRepository.findOne(topicId)
    }
}