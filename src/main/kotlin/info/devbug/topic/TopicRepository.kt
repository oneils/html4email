package info.devbug.topic

import org.springframework.data.repository.CrudRepository

/**
 * @author Aliaksei Bahdanau
 */
interface TopicRepository : CrudRepository<TopicDto, Int> {

    override fun findAll(): List<TopicDto>
}