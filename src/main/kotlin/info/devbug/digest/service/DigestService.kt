package info.devbug.digest.service

import info.devbug.digest.repository.DigestDto
import org.springframework.data.domain.Page

/**
 * @author Aliaksei Bahdanau
 */
interface DigestService {

    fun findAll(page: Int, size: Int): Page<DigestDto>
}