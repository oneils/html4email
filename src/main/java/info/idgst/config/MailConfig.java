package info.idgst.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author Aliaksei Bahdanau
 */
@Configuration
public class MailConfig {

    @Autowired IdgstConfigReader idgstConfigReader;

    @Bean
    public JavaMailSenderImpl mailSender(Environment env) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(getProperties(env));
        mailSender.setHost(env.getProperty("email.smtp.host"));
        mailSender.setUsername(env.getProperty("email.login"));
        mailSender.setPassword(env.getProperty("email.password"));
        mailSender.setDefaultEncoding("UTF-8");

        return mailSender;
    }

    private Properties getProperties(Environment env) {
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", env.getProperty("email.smtp.ttlsEnable"));
        return properties;
    }
}
