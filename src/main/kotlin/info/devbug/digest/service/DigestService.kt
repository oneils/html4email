package info.devbug.digest.service

import info.devbug.digest.repository.DigestDto

/**
 * @author Aliaksei Bahdanau
 */
interface DigestService {

    fun findAll(): List<DigestDto>
}