package info.idgst.digest;

import java.util.Map;

/**
 * Interface for template processor. Generates the {@link String} representation of the Digest according to the
 * specified model.
 *
 * @author Aliaksei Bahdanau
 */
public interface DigestTemplateProcessor {

    /**
     * Generates the String representation of the Digest from the model specified.
     *
     * @param model {@link Map}
     * @return {@link String} representation of the Digest
     */
    String generateDigest(Map<String, Object> model);
}
