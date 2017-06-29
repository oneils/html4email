package info.idgst.config.mail

import info.idgst.AbstractTest
import info.idgst.config.mail.provider.MailPropertiesProvider
import info.idgst.config.mail.provider.MailProviderFactory
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.core.env.Environment
import org.springframework.mock.env.MockEnvironment
import java.util.*

/**
 * Test for [MailConfig].
 *
 * @author Aliaksei Bahdanau
 */
class MailConfigTest : AbstractTest() {

    private val mailProviderFactory = mock(MailProviderFactory::class.java)
    private val mailConfig = MailConfig(mailProviderFactory)
    private val env: Environment = mock(MockEnvironment::class.java)
    private val mailPropertiesProvider = mock(MailPropertiesProvider::class.java)

    override fun before() {
        val mailProviderName = "provider"
        `when`(env.getProperty("email.provider")).thenReturn(mailProviderName)
        `when`(mailProviderFactory.getProvider(mailProviderName)).thenReturn(mailPropertiesProvider)

        `when`(mailPropertiesProvider.getMailProperties()).thenReturn(Properties())
    }

    @Test
    fun `mailSender should return configured mail sender`() {
        // Setup
        val host = "email.smtp.host"
        `when`(env.getProperty("email.smtp.host")).thenReturn(host)
        val login = "email.login"
        `when`(env.getProperty("email.login")).thenReturn(login)
        val password = "email.password"
        `when`(env.getProperty("email.password")).thenReturn(password)

        // Run
        val result = mailConfig.mailSender(env)

        // Verify

        assertThat(result.host, `is`(host))
        assertThat(result.username, `is`(login))
        assertThat(result.password, `is`(password))
        assertThat(result.defaultEncoding, `is`("UTF-8"))
    }

}
