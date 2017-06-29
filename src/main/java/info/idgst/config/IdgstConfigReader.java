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

    @Value("${digest.archiveHost}")
    private String archiveHost;

    @Value("${digest.templateName}")
    private String templateName = "digest.ftl";

    @Value("${digest.contributeTo}")
    private String contributeTo;

    @Value("${digest.companyName}")
    private String companyName;

    public String getArchiveHost() {
        return archiveHost;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getContributeTo() {
        return contributeTo;
    }

    public String getCompanyName() {
        return companyName;
    }
}
