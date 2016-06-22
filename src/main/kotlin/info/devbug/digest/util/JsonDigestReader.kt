package info.devbug.digest.util

import com.google.gson.Gson
import info.devbug.digest.repository.DigestDto
import org.springframework.stereotype.Service
import java.io.File

/**
 * @author Aliaksei Bahdanau
 */
@Service
class JsonDigestReader : DigestReader {

    override fun readDigest(filePath: String): DigestDto {
        val gson = Gson()

        val digestJsonText = File(filePath).readText()
        return gson.fromJson(digestJsonText, DigestDto::class.java)
    }
}