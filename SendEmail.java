package apu.gym.centre.management.system;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail{
    public static boolean send(String to, String subject, String content) { 
        // Configuration of SMTP Server
        // Use system properties to setup mail server
        String from = "oodj@befamous.cyou"; 
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.dreamhost.com");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.ssl.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        // Get the mail authenticator session object and pass with password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(from, "ARf5feDV");
                                }
                            });

        //Create MimeMessage using the session created and configure the mail content
        try {
           MimeMessage message = new MimeMessage(session);
           message.setFrom(new InternetAddress(from));
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
           message.setSubject(subject);
           message.setText(content);
           Transport.send(message);
           return true;
        } catch (MessagingException mex) {
           return false;
        }
   }
}