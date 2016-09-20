package info.devbug.digest.service

import info.devbug.digest.DigestCache
import info.devbug.digest.repository.DigestDto
import info.devbug.digest.util.DigestParser
import info.devbug.digest.repository.DigestRepository
import info.devbug.digest.util.DigestReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

/**
 * Default implementation of [DigestService].
 *
 * @author Aliaksei Bahdanau.
 */
@Service
open class DefaultDigestService : DigestService{

    private val digestRepository: DigestRepository
    private val digestReader: DigestReader
    private val digestCache: DigestCache

    @Autowired constructor(digestRepository: DigestRepository, digestReader: DigestReader, digestCache: DigestCache) {
        this.digestRepository = digestRepository
        this.digestReader = digestReader
        this.digestCache = digestCache
    }

    /**
     * Returns the [DigestDto] object according to specified the digest file path.
     */
    fun readDigestFromFile(filePath: String): DigestDto {
        return digestReader.readDigest(filePath)
    }

    /**
     * Parses [DigestDto] title and returns current digest number.
     */
    fun getDigestNumber(digestTitle: String): String {
        val digestParser = DigestParser()
        return digestParser.getDigestNumber(digestTitle)
    }

    override fun findAll(page: Int, size: Int): Page<DigestDto> {
        return digestCache.fetch(page, size)
    }

    override fun save(digest: DigestDto): DigestDto {
        val existingDigest = digestCache.fetchByTitle(digest.title)

        if (existingDigest != null && existingDigest.title == digest.title) throw Exception("Already exists")
        return digestRepository.save(digest)
    }

    @Cacheable("searches")
    override fun findById(id: String): DigestDto? {
        return digestCache.fetch(id)
    }
}