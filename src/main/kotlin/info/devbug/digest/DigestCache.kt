package info.devbug.digest

import info.devbug.digest.repository.DigestDto
import info.devbug.digest.repository.DigestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

/**
 * Cache for search result of [DigestDto]s.
 *
 * @author Aliaksei Bahdanau
 */
@Service
open class DigestCache {

    private val digestRepository: DigestRepository

    @Autowired constructor(digestRepository: DigestRepository) {
        this.digestRepository = digestRepository
    }


    @Cacheable("searches")
    fun fetch(page: Int, size: Int): Page<DigestDto> {
        val request: PageRequest =  PageRequest(page, size, Sort.Direction.DESC, "publishedDate")
        return digestRepository.findAll(request)
    }

    @Cacheable("searches")
    fun fetch(id: String): DigestDto? {
        return digestRepository.findById(id)
    }

    @Cacheable("searches")
    fun fetchByTitle(title: String): DigestDto? {
        return digestRepository.findByTitle(title)
    }
}