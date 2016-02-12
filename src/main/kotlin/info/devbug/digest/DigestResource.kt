package info.devbug.digest

import info.devbug.digest.repository.DigestDto
import info.devbug.digest.service.DigestServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

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
    fun digests(@RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
                @RequestParam(value = "size", required = false, defaultValue = "10") size: Int):
            ResponseEntity<Page<DigestDto>> {
        val digests = digestService.findAll(page, size)
        return ResponseEntity(digests, HttpStatus.OK)
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"))
    fun save(@RequestBody digest: DigestDto): ResponseEntity<DigestDto> {
        val savedDigest = digestService.save(digest)

        val responseHeaders: HttpHeaders = HttpHeaders()
        val newPollUri: URI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDigest.id)
                .toUri();
        responseHeaders.location = newPollUri;

        return ResponseEntity(savedDigest, responseHeaders, HttpStatus.CREATED)
    }
}