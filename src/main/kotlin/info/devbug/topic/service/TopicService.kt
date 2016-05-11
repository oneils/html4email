package info.devbug.topic.service

import info.devbug.topic.TopicDto

/**
 * @author Aliaksei Bahdanau
 */
interface TopicService {

    fun findAll(): List<TopicDto>

    fun findById(topicId: Int): TopicDto
}