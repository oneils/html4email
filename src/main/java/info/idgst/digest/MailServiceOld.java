package info.idgst.digest;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by abahdanau on 8.6.17.
 */
public class MailServiceOld {

//    public void prepareMail() throws MessagingException, IOException {
//        // if text part is present
////        MimeBodyPart textPart = new MimeBodyPart();
////        textPart.setText(mailBody);
////        alternative.addBodyPart(textPart);
//
//
//        MimeMultipart alternative = new MimeMultipart("alternative");
//        MimeBodyPart htmlPart = new MimeBodyPart();
//        htmlPart.setContent(htmlContent, "text/html; charset=UTF-8");
//        alternative.addBodyPart(htmlPart);
//
//        MimeMultipart multipart = getMimeMultipart(alternative);
//
//
//        Transport.send(prepareMessage(multipart));
//    }
//
//    private MimeMultipart getMimeMultipart(MimeMultipart alternative) throws MessagingException, IOException {
//        MimeMultipart multipart = new MimeMultipart();
//        MimeBodyPart wrap = new MimeBodyPart();
//        wrap.setContent(alternative);
//        multipart.addBodyPart(wrap);
//
//        MimeBodyPart attachmentPart = new MimeBodyPart();
//        attachmentPart.attachFile("fileName");
//        multipart.addBodyPart(attachmentPart);
//
//        MimeBodyPart imagePart = new MimeBodyPart();
//        imagePart.attachFile("imageName");
//        imagePart.setContentID("<" + imageID + ">");
//        multipart.addBodyPart(imagePart);
//        return multipart;
//    }
//
//    private MimeMessage prepareMessage(MimeMultipart multipart) throws MessagingException {
//        String userName = "";
//        String password = "";
//
//        MimeMessage message = new MimeMessage(getSession(userName, password));
//        message.setFrom(fromUser);
//        message.addRecipients(Message.RecipientType.TO, "recipients");
//        message.setSubject(mailSubject);
//        message.setContent(multipart);
//        return message;
//    }
//
//    private Session getSession(final String username, final String password) {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", "smtp.yandex.com");
//        properties.put("mail.smtp.starttls.enable", "true");
////        return Session.getDefaultInstance(properties);
//        return Session.getInstance(properties,
//                new Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//    }


}
