package info.devbug.topic.service

import info.devbug.topic.TopicDto
import info.devbug.topic.TopicRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Aliaksei Bahdanau
 */

@Service
open class TopicServiceImpl : TopicService{
    private val topicRepository: TopicRepository

    @Autowired constructor(topicRepository: TopicRepository) {
        this.topicRepository = topicRepository
    }

    override fun findAll(): List<TopicDto> {
        return topicRepository.findAll()
    }

    override fun findById(topicId: Int): TopicDto {
        return topicRepository.findOne(topicId)
    }
}