package info.idgst.config.mail.provider

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import java.util.*

/**
 * Default implementation of the [MailPropertiesProvider].
 *
 * @author Aliaksei Bahdanau
 */
@Service
class DefaultMailPropertiesProvider @Autowired constructor(private val env: Environment) : MailPropertiesProvider {
    override fun getMailProperties(): Properties {
        val properties = Properties()
        properties.put("mail.smtp.starttls.enable", env.getProperty("email.smtp.ttlsEnable"))
        return properties
    }
}