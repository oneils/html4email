package info.idgst.config.mail

import info.idgst.config.mail.provider.MailProviderFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

/**
 * Configuration for [JavaMailSenderImpl]
 *
 * @author Aliaksei Bahdanau
 */
@Configuration
class MailConfig @Autowired constructor(private val mailProviderFactory: MailProviderFactory) {

    @Bean
    fun mailSender(env: Environment): JavaMailSenderImpl {
        val mailSender = JavaMailSenderImpl()
        mailSender.javaMailProperties = getProperties(env)
        mailSender.host = env.getProperty("email.smtp.host")
        mailSender.username = env.getProperty("email.login")
        mailSender.password = env.getProperty("email.password")
        mailSender.defaultEncoding = "UTF-8"

        return mailSender
    }

    private fun getProperties(env: Environment): Properties {
        val mailProvider = env.getProperty("email.provider")
        val mailPropertiesProvider = mailProviderFactory.getProvider(mailProvider)
        return mailPropertiesProvider.getMailProperties()
    }
}
