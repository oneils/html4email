package info.idgst.digest.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import info.idgst.digest.Digest;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Implementation of {@link DigestReader} for reading {@link Digest}s from JSON Files.
 *
 * @author Aliaksei Bahdanau
 */
@Service
public class JsonDigestReader implements DigestReader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @NotNull
    private Gson getGson() {
        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        JsonDeserializer jsonDeserializer = (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive()
                                                                                     .getAsLong());
        builder.registerTypeAdapter(Date.class, jsonDeserializer);
        return builder.create();
    }

    @Override
    public Digest readDigest(byte[] bytes){
        String jsonString = null;
        try {
            jsonString = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Error while converting bytes to String", e);
        }
        logger.debug("Deserializing Digest from json " + jsonString);
        return getGson().fromJson(jsonString, Digest.class);
    }
}
