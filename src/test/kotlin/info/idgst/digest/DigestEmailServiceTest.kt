package info.idgst.digest

import info.idgst.AbstractTest
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.core.io.FileSystemResource
import java.lang.RuntimeException

/**
 * Test for [DigestEmailService]
 */
class DigestEmailServiceTest : AbstractTest() {
    @Mock private lateinit var mailService: MailService
    @Mock private lateinit var digestTemplateProcessor: DigestTemplateProcessor

    private lateinit var digestEmailService: DigestEmailService

    override fun before() {
        super.before()

        digestEmailService = DigestEmailService(mailService, digestTemplateProcessor)
    }

    @Test(expected = RuntimeException::class)
    fun `sendEmail should not send empty template`() {
        // Setup
        `when`(digestTemplateProcessor.generateTemplateDigest(any())).thenReturn("")

        // Run
        digestEmailService.sendViaEmail(emptyMap())

        // Verify
        verify(mailService, never()).sendEmail(Matchers.any(), Matchers.any())
    }

    @Test
    fun `sendEmail should send generated html template via email`() {
        // Setup
        val digestNumber = 123
        val subject = "Digest Title"
        val sendTo = "noreply1@email.com,noreply2@mail.com"
        val recipients = sendTo.split(",").toTypedArray()
        val model = mapOf("digestNumber" to digestNumber, "digestTitle" to subject, "sendTo" to sendTo)
        val digestHtmlTemplate = "generated html content"
        `when`(digestTemplateProcessor.generateTemplateDigest(any())).thenReturn(digestHtmlTemplate)

        val digestLogoImg = FileSystemResource("web-src/app/images/logos/$digestNumber.png")

        // Run
        digestEmailService.sendViaEmail(model)

        // Verify
        verify(mailService).sendEmail(EmailMessage(digestHtmlTemplate, true, subject, EmailAttachment(digestLogoImg)), recipients)
    }

}
