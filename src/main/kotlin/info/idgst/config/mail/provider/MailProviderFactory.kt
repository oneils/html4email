package info.idgst.config.mail.provider

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Factory for mail providers. Returns an appropriate implementation of the [MailPropertiesProvider].
 *
 * @author Aliaksei Bahdanau
 */
@Service
class MailProviderFactory @Autowired constructor(private val zohoMailPropertiesProvider: ZohoMailPropertiesProvider,
                                                 private val defaultMailPropertiesProvider: DefaultMailPropertiesProvider) {

    fun getProvider(mailProvider: String?): MailPropertiesProvider {
        val provider = when (mailProvider) {
            "zoho" -> zohoMailPropertiesProvider
            else -> {
                defaultMailPropertiesProvider
            }
        }
        return provider
    }
}