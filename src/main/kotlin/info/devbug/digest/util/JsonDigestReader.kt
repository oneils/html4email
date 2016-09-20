package info.devbug.digest.util

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import info.devbug.digest.repository.DigestDto
import org.springframework.stereotype.Service
import java.io.File
import java.util.*

/**
 * @author Aliaksei Bahdanau
 */
@Service
class JsonDigestReader : DigestReader {

    override fun readDigest(filePath: String): DigestDto {
        // Creates the json object which will manage the information received
        val builder = GsonBuilder()

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date::class.java, JsonDeserializer { json, typeOfT, context -> Date(json.asJsonPrimitive.asLong) })
        val gson = builder.create()

        val digestJsonText = File(filePath).readText()
        return gson.fromJson(digestJsonText, DigestDto::class.java)
    }
}