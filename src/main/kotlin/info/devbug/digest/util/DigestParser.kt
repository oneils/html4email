package info.devbug.digest.util

/**
 * @author Aliaksei Bahdanau.
 */
class DigestParser {
    private val DIGEST_NUMBER_DELIMITER = "#"
    val DEFAULT_DIGEST_NUMBER = "0"

    /**
     * Parses [DigestDto] title and returns current digest number.
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