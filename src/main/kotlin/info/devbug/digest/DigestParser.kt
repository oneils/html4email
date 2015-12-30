package info.devbug.digest

import com.google.gson.Gson
import info.devbug.api.Digest
import java.io.BufferedReader
import java.io.FileReader

/**
 * @author Aliaksei Bahdanau.
 */
class DigestParser {
    private val DIGEST_NUMBER_DELIMITER = "#"
    val DEFAULT_DIGEST_NUMBER = "0"

    /**
     * Parses the specified Digest Json file and returns the Digest
     */
    fun getDigest(digestJsonPath: String): Digest {
        val gson = Gson()

        val br = BufferedReader(
                FileReader(digestJsonPath));

        val digest = gson.fromJson(br, Digest::class.java)
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