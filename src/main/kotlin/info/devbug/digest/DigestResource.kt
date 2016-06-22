package info.devbug.digest

import info.devbug.api.RestException
import info.devbug.digest.repository.DigestDto
import info.devbug.digest.service.DigestService
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
class DigestResource @Autowired constructor(val digestService: DigestService) {

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
        val savedDigest: DigestDto
        try {
            savedDigest = digestService.save(digest)
        } catch(e: Exception) {
            throw RestException(0, "Error while saving Digest",
                    "Digest with such title already exists.")
        }

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