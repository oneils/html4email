package info.devbug.digest

import info.devbug.article.ArticleService
import info.devbug.digest.repository.DigestDto
import info.devbug.digest.service.DigestServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author Aliaksei Bahdanau
 */
@RestController
@RequestMapping(value = "/v1/digests")
class DigestResource {
    private val digestService: DigestServiceImpl

    @Autowired constructor(digestService: DigestServiceImpl) {
        this.digestService = digestService
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun digests(): ResponseEntity<List<DigestDto>> {
        val digests = digestService.findAll()
        return ResponseEntity(digests, HttpStatus.OK)
    }
}