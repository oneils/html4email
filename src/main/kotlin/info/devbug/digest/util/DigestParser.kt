package info.devbug.digest.util

import com.google.gson.Gson
import info.devbug.digest.repository.DigestDto
import java.io.BufferedReader
import java.io.FileReader
import kotlin.text.split

/**
 * @author Aliaksei Bahdanau.
 */
class DigestParser {
    private val DIGEST_NUMBER_DELIMITER = "#"
    val DEFAULT_DIGEST_NUMBER = "0"

    /**
     * Parses the specified Digest Json file and returns the Digest
     */
    fun getDigest(digestJsonPath: String): DigestDto {
        val gson = Gson()

        val br = BufferedReader(
                FileReader(digestJsonPath));

        val digest = gson.fromJson(br, DigestDto::class.java)
        return digest
    }

    /**
     * Parses [Digest] title and returns current digest number.
     */
    fun getDigestNumber(digestTitle: String): String {
        val digestValidator = DigestValidator()
        if (digestValidator.isTitleValid(digestTitle)) {
            return digestTitle.split(DIGEST_NUMBER_DELIMITER)[1]
        } else {
            return DEFAULT_DIGEST_NUMBER
        }
    }
}