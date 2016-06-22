package info.devbug.digest.util

import info.devbug.digest.repository.DigestDto

/**
 * @author Aliaksei Bahdanau
 */
interface DigestReader {

    /**
     * Reads the Digest from the specified file.
     */
    fun readDigest(filePath: String): DigestDto
}