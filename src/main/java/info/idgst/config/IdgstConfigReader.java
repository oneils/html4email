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

    @Value("${digest.numberDelimiter}")
    private String digestNumberDelimiter = "#";

    @Value("${digest.templateName}")
    private String templateName = "digest.ftl";

    @Value("${email.replyTo}")
    private String replyTo;

    @Value("${digest.contributeTo}")
    private Object contributeTo;

    @Value("${digest.companyName}")
    private Object companyName;

    @Value("${email.sendTo}")
    private String sendToEmail;

    @Value("${email.login}")
    private String emailLogin;

    @Value("${email.password}")
    private String emailPassword;

    @Value("${email.smtp.host}")
    private Object smtpHost;

    @Value("${email.smtp.ttlsEnable}")
    private boolean ttlsEnable;

    public String getArchiveHost() {
        return archiveHost;
    }

    public String getDigestNumberDelimiter() {
        return digestNumberDelimiter;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public Object getContributeTo() {
        return contributeTo;
    }

    public Object getCompanyName() {
        return companyName;
    }

    public String getSendToEmail() {
        return sendToEmail;
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public Object getSmtpHost() {
        return smtpHost;
    }

    public boolean isTtlsEnable() {
        return ttlsEnable;
    }
}
