package info.devbug.digest.service

import info.devbug.digest.repository.DigestDto
import info.devbug.digest.util.DigestParser
import info.devbug.digest.repository.DigestRepository
import info.devbug.digest.util.DigestReader
import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired constructor(digestRepository: DigestRepository, digestReader: DigestReader) {
        this.digestRepository = digestRepository
        this.digestReader = digestReader
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
        val request: PageRequest =  PageRequest(page, size, Sort.Direction.DESC, "publishedDate");
        return digestRepository.findAll(request)
    }

    override fun save(digest: DigestDto): DigestDto {
        val existingDigest = digestRepository.findByTitle(digest.title)

        if (existingDigest != null && existingDigest.title == digest.title) throw Exception("Already exists")
        return digestRepository.save(digest)
    }

    override fun findById(id: String): DigestDto? {
        val digest = digestRepository.findById(id)

        return digest
    }
}