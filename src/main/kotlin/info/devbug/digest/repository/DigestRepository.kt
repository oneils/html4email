package info.devbug.digest.repository

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

/**
 * @author Aliaksei Bahdanau
 */

@Repository
interface DigestRepository : PagingAndSortingRepository<DigestDto, Int> {

    fun findByTitle(title: String): DigestDto?

    /**
     * Finds [DigestDto] by specified ID.
     *
     * @return [DigestDto]
     */
    fun findById(id: String): DigestDto?
}