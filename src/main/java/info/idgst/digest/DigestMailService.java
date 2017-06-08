package info.idgst.digest;

import info.idgst.config.IdgstConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by abahdanau on 8.6.17.
 */
@Service
public class DigestMailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IdgstConfigReader idgstConfigReader;

    @Autowired
    public DigestMailService(IdgstConfigReader idgstConfigReader) {
        this.idgstConfigReader = idgstConfigReader;
    }


    public void sendDigest(String digestHtml, String digestTitle, int digestNumber) {
        // Create a default MimeMessage object.
        Message message = getMessage(digestHtml, digestTitle, digestNumber);

        final String username = idgstConfigReader.getEmailLogin();
        final String password = idgstConfigReader.getEmailPassword();
        try {
            // Send message
            Transport.send(message, username, password);
        } catch (MessagingException e) {
            logger.error("Error while sending email", e);
        }

        logger.info("Email sent successful");
    }

    private Message getMessage(String digestHtml, String mailSubject, int digestNumber) {
        Message message = new MimeMessage(getSession());

        try {
            // Sender's email ID needs to be mentioned
            String from = idgstConfigReader.getReplyTo();
            message.setFrom(new InternetAddress(from));

            // Recipient's email ID needs to be mentioned.
            String to = idgstConfigReader.getSendToEmail();
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(mailSubject);

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");
            multipart.addBodyPart(createHtmlPart(digestHtml));
            multipart.addBodyPart(createImagePart(digestNumber));

            // put everything together
            message.setContent(multipart);

        } catch (MessagingException e) {
            logger.error("Error while processing the message", e);
        }
        return message;
    }

    private MimeBodyPart createImagePart(int digestNumber) throws MessagingException {
        MimeBodyPart imgPart = new MimeBodyPart();
        DataSource fds = new FileDataSource("web-src/app/images/logos/" + digestNumber + ".png");

        imgPart.setDataHandler(new DataHandler(fds));
        imgPart.setHeader("Content-ID", "<image>");
        imgPart.setFileName("Digest logo");
        return imgPart;
    }

    private BodyPart createHtmlPart(String digestHtml) throws MessagingException {
        // first part (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        // Charset should be set to correctly display non english text
        messageBodyPart.setContent(digestHtml, "text/html; charset=UTF-8");

        return messageBodyPart;
    }

    private Session getSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", idgstConfigReader.getSmtpHost());
        properties.put("mail.smtp.starttls.enable", idgstConfigReader.isTtlsEnable());

        return Session.getDefaultInstance(properties);
    }
}
