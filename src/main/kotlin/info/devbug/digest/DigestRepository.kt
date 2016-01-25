package info.devbug.digest

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Aliaksei Bahdanau
 */

@Repository
interface  DigestRepository : CrudRepository<DigestDto, Int> {

    override fun findAll(): List<DigestDto>
}