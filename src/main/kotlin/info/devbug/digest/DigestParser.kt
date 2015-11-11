package info.devbug.digest

import com.google.gson.Gson
import info.devbug.api.Digest
import java.io.BufferedReader
import java.io.FileReader

/**
 * @author Aliaksei Bahdanau.
 */
class DigestParser {

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
}