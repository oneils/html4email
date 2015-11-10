package info.devbug.service

import info.devbug.api.Digest
import org.junit.Test

import org.junit.Assert.*

/**
 * @author Aliaksei Bahdanau.
 */
class DigestServiceTest {

    @Test
    fun testTest1() {
        val service = DigestService()

        val digest = service.test()

        digest
    }
}