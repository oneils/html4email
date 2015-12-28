package info.devbug.digest

import info.devbug.api.Digest
import org.springframework.stereotype.Service

/**
 * @author Aliaksei Bahdanau.
 */
@Service
class DigestService {

    /**
     * Returns the Digest object according to specified the digest file path.
     */
    fun getDigest(filePath: String): Digest {
        val digestParser = DigestParser()
        return digestParser.getDigest(filePath)
    }
}