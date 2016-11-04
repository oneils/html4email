package info.idgst.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Component for reading application properties.
 *
 * @author Aliaksei Bahdanau
 */
@Component
public class IdgstConfigReader {

    @Value("${archiveHost}")
    private String archiveHost;

    public String getArchiveHost() {
        return archiveHost;
    }
}
