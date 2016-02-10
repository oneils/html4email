package info.devbug.digest

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

/**
 * @author Aliaksei Bahdanau
 */

@Repository
interface DigestRepository : PagingAndSortingRepository<DigestDto, Int> {

    override fun findAll(): List<DigestDto>

}