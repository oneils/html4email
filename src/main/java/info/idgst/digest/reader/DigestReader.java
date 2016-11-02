package info.idgst.digest.reader;

import info.idgst.digest.Digest;

/**
 * Service foe reading {@link Digest}s form different files, like JSON, xml, etc.
 *
 * @author Aliaksei Bahdanau
 */
public interface DigestReader {

    /**
     * Converts specified bytes to {@link Digest}.
     *
     * @param bytes
     * @return {@link Digest}
     */
    Digest readDigest(byte[] bytes);
}
