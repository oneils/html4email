package info.devbug.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * Component for reading application properties.
 *
 * @author Aliaksei Bahdanau
 */
@Component
open class AppConfig {


    @Value("\${archiveHost}")
    val archiveHost: String? = null

}