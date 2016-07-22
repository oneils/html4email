package info.devbug.integration

import org.apache.commons.lang3.RandomStringUtils
import org.apache.http.HttpStatus
import org.apache.http.client.methods.HttpGet
import org.apache.http.entity.ContentType
import org.apache.http.impl.client.HttpClientBuilder
import org.junit.Test

/**
 * Integration test for [DigestResource]
 *
 * @author Aliaksei Bahdanau.
 */
class DigestApiIntegrationTest : BaseIntegrationTest() {

    val digestApiUrl = "/v1/digests/"

    @Test
    @Throws(Exception::class)
    fun `resource doesn't exist`() {
        // Given
        val name = RandomStringUtils.randomAlphabetic(8)
        val request = HttpGet("http://localhost/v1/digests/" + name)

        // When
        val httpResponse = HttpClientBuilder.create().build().execute(request)

        // Then
        assert(httpResponse.statusLine.statusCode == HttpStatus.SC_NOT_FOUND)
    }

    @Test
    @Throws(Exception::class)
    fun `all digest returned as JSON`() {
        // Given
        val request = HttpGet("http://$serverHost:$serverPort$digestApiUrl")
        val jsonMimeType = "application/json"

        // When
        val httpResponse = HttpClientBuilder.create().build().execute(request)

        // Then
        assert(httpResponse.statusLine.statusCode == HttpStatus.SC_OK)

        val mimeType: String = ContentType.getOrDefault(httpResponse.entity).mimeType
        assert(jsonMimeType == mimeType)
    }
}