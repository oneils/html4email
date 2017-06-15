package info.idgst.digest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import javax.mail.internet.MimeMessage

/**
 * Service for sending the Digest via email.
 *
 * @author Aliaksei Bahdanau
 */
@Service
class MailService
@Autowired constructor(private val mailSender: JavaMailSender, private val env: Environment) {

    fun sendEmail(emailMessage: EmailMessage, sendTo: Array<String>) {
        val message = mailSender.createMimeMessage()

        addAttachment(emailMessage.emailAttachment, mimeMessageHelper(message, sendTo, emailMessage))

        mailSender.send(message)
    }

    private fun mimeMessageHelper(message: MimeMessage?, sendTo: Array<String>, emailMessage: EmailMessage): MimeMessageHelper {
        val helper = MimeMessageHelper(message, true)
        helper.setBcc(sendTo)
        helper.setFrom(env.getProperty("email.replyTo"))
        helper.setSubject(emailMessage.subject)
        helper.setText(emailMessage.content, emailMessage.isContentHtml)
        return helper
    }

    private fun addAttachment(emailAttachment: EmailAttachment, helper: MimeMessageHelper) {
        val fileResource = emailAttachment.fileResource
        if (emailAttachment.inline) {
            helper.addInline("digest-logo", fileResource)
        } else {
            helper.addAttachment("digest.png", fileResource)
        }
    }
}
