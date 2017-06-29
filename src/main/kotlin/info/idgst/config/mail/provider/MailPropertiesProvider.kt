package info.idgst.config.mail.provider

import java.util.*

/**
 * Email properties provider. If provider's properties differ form the default implementation provider should implement
 * this interface.
 *
 * @author Aliaksei Bahdanau
 */

interface MailPropertiesProvider {

    fun getMailProperties(): Properties
}