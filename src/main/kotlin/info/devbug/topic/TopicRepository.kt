package info.devbug.topic

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Aliaksei Bahdanau
 */
@Repository
interface TopicRepository : CrudRepository<TopicDto, Int> {

    override fun findAll(): List<TopicDto>
}