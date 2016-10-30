package info.idgst.digest.reader;

import info.idgst.digest.Digest;

/**
 * Service foe reading {@link Digest}s form different files, like JSON, xml, etc.
 *
 * @author Aliaksei Bahdanau
 */
public interface DigestReader {

    /**
     * Read the file by specified path and returns {@link Digest}.
     *
     * @param filePath path to the file form with to read digest
     * @return {@link Digest}
     */
    Digest readDigest(String filePath);
}
