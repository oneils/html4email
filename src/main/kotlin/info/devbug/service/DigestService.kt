package info.devbug.service

import com.github.salomonbrys.kotson.simpleJsonDeserializer
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import info.devbug.api.Digest
import java.io.BufferedReader
import java.io.FileReader

/**
 * @author Aliaksei Bahdanau.
 */
class DigestService {

    fun test(): Digest  {
        val gson = Gson()

        val jsonFile = "/home/abahdanau/projects/html4email-kotlin/src/test/resources/digest.json"
        val reader = JsonReader(FileReader(jsonFile))
//        val result = gson.fromJson<List<Digest>>(reader)
        val br = BufferedReader(
                FileReader(jsonFile));

        val digest = gson.fromJson(br, Digest::class.java)
        return digest
    }
}