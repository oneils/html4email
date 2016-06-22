package info.devbug.digest.util

/**
 * @author Aliaksei Bahdanau.
 */
class DigestValidator {
    private val DIGEST_TITLE_PATTERN = "[A-Za-z0-9_ ]+[#]{1}[0-9]+"

    /**
     * Verifies is Digest has valid [title] or not.
     *
     * Returns true if title is valid, false otherwise.
     */
    fun isTitleValid(title: String): Boolean {
        val regex = Regex(DIGEST_TITLE_PATTERN)
        return regex.matches(title)
    }
}