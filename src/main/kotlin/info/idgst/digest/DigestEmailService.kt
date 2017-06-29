package info.idgst.digest

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class DigestEmailService
@Autowired constructor(private val mailService: MailService,
                       private val digestTemplateProcessor: DigestTemplateProcessor) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Retrievs the html template from the model specified and sends it via email.
     */
    fun sendViaEmail(model: Map<String, Any>) {
        val digestHtmlContent = digestTemplateProcessor.generateTemplateDigest(model)

        if (digestHtmlContent.isNotEmpty()) {
            val digestNumber = model["digestNumber"] as Int
            val digestTitle = model["digestTitle"] as String
            val sendToStr = model["sendTo"] as String

            val digestLogoImg = FileSystemResource("images/logos/$digestNumber.png")
            val emailMessage = EmailMessage(digestHtmlContent, true, digestTitle,
                    EmailAttachment(digestLogoImg))

            val sendTo: Array<String> = extractRecipient(sendToStr)
            mailService.sendEmail(emailMessage, sendTo)
        } else {
            val msg = "Digest was not sent via email because digest template is empty"
            logger.warn(msg)
            throw RuntimeException(msg)
        }
    }

    private fun extractRecipient(sendToStr: String) = sendToStr.trim().replace(" ", "").
            split(",").toTypedArray()
}