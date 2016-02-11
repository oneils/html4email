package info.devbug.digest.repository

import org.springframework.data.domain.Page
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

/**
 * @author Aliaksei Bahdanau
 */

@Repository
interface DigestRepository : PagingAndSortingRepository<DigestDto, Int> {

}