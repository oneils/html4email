package info.devbug.digest.service

import info.devbug.digest.repository.DigestDto
import org.springframework.data.domain.Page

/**
 * Service to operate with [DigestDto]
 *
 * @author Aliaksei Bahdanau
 */
interface DigestService {

    fun findAll(page: Int, size: Int): Page<DigestDto>

    fun save(digest: DigestDto): DigestDto

    /**
     * Finds [DigestDto] by specified Digest's ID.
     *
     * @return [DigestDto] if Digest is found, throws exception if Digest is not found
     */
    fun findById(id: String): DigestDto
}