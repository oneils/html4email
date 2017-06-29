package info.idgst.config.mail.provider

import org.springframework.stereotype.Service
import java.util.*

/**
 * Zoho properties provider implementation of the [MailPropertiesProvider]
 *
 * @author Aliaksei Bahdanau
 */
@Service
class ZohoMailPropertiesProvider : MailPropertiesProvider {

    override fun getMailProperties(): Properties {
        val properties = Properties()
        properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        properties.setProperty("mail.smtp.socketFactory.fallback", "false")
        properties.setProperty("mail.smtp.socketFactory.port", "465")
        properties.put("mail.smtp.startssl.enable", "true")
        return properties
    }
}