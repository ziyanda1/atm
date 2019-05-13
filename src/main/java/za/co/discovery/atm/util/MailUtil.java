package za.co.discovery.atm.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class MailUtil {
    public static void sendmail(String message) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("support@thebank.com", "password");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("support@thebank.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("support@thebank.com"));
        msg.setSubject("Monthly Client Report");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(message);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        msg.setContent(multipart);
        Transport.send(msg);
    }
}
