package info.devbug.integration.digest

import info.devbug.digest.repository.DigestRepository
import info.devbug.digest.util.DigestReader
import info.devbug.digest.util.JsonDigestReader
import info.devbug.integration.AbstractIntegrationTest
import org.apache.commons.lang3.RandomStringUtils
import org.apache.http.HttpStatus
import org.apache.http.client.methods.HttpGet
import org.apache.http.entity.ContentType
import org.apache.http.impl.client.HttpClientBuilder
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Integration test for DigestResource
 *
 * @author Aliaksei Bahdanau.
 */
class DigestResourceIntegrationTest : AbstractIntegrationTest() {

    val digestApiUrl = "/v1/digests/"

    lateinit private var digestReader: DigestReader

    @Autowired lateinit private var digestRepository: DigestRepository

    @Before
    fun setUp() {
        digestReader = JsonDigestReader()

        val digest = digestReader.readDigest("src/test/resources/digest.json")

        digestRepository.save(digest)
    }

    @After
    fun cleanUp() {
        digestRepository.deleteAll()
    }

    @Test
    @Throws(Exception::class)
    fun `resource doesn't exist`() {
        // Given
        val name = RandomStringUtils.randomAlphabetic(8)
        val request = HttpGet("$protocol://$serverHost:$serverPort$digestApiUrl$name")

        // When
        val httpResponse = HttpClientBuilder.create().build().execute(request)

        // Then
        assert(httpResponse.statusLine.statusCode == HttpStatus.SC_NOT_FOUND)
    }

    @Test
    @Throws(Exception::class)
    fun `all digest returned as JSON`() {
        // Given
        val request = HttpGet("$protocol://$serverHost:$serverPort$digestApiUrl")
        val jsonMimeType = "application/json"

        // When
        val httpResponse = HttpClientBuilder.create().build().execute(request)

        // Then
        assert(httpResponse.statusLine.statusCode == HttpStatus.SC_OK)

        val mimeType: String = ContentType.getOrDefault(httpResponse.entity).mimeType
        assert(jsonMimeType == mimeType)
    }
}