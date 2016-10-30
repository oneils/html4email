package info.idgst.digest.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import info.idgst.digest.Digest;
import info.idgst.exception.IdgstException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Implementation of {@link DigestReader} for reading {@link Digest}s from JSON Files.
 *
 * @author Aliaksei Bahdanau
 */
@Service
public class JsonDigestReader implements DigestReader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Digest readDigest(String filePath) {
        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        JsonDeserializer jsonDeserializer = (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong());
        builder.registerTypeAdapter(Date.class, jsonDeserializer);
        Gson gson = builder.create();

        String digestJsonText = jsonToText(filePath);
        return gson.fromJson(digestJsonText, Digest.class);
    }

    private String jsonToText(String filePath) {
        String digestJsonText = null;
        try {
            digestJsonText = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            String msg = "Error while reading specified file";
            logger.error(msg, e);
            throw new IdgstException(msg, e);
        }
        return digestJsonText;
    }
}
