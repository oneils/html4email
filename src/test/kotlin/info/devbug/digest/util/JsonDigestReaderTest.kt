package info.devbug.digest.util

import com.google.gson.JsonSyntaxException
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

/**
 * @author Aliaksei Bahdanau
 */
class JsonDigestReaderTest {

    lateinit private var digestReader: DigestReader

    @Before
    fun setUp() {
        digestReader = JsonDigestReader()
    }

    @Test
    fun `readDigest should return DigestDto with 2 topics`() {
        val result = digestReader.readDigest("src/test/resources/digest.json")

        assert(result.topics.size == 2)
    }

    @Test(expected = FileNotFoundException::class)
    fun `readDigest should throw Exception for incorrect file name or path`() {
        digestReader.readDigest("unknown_file_path.json")
    }

    @Test(expected = JsonSyntaxException::class)
    fun `readDigest should throw Exception for invalid JSON`() {
        digestReader.readDigest("src/test/resources/invalid-json.json")
    }
}