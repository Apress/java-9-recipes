package org.java9recipes.chapter19.recipe19_04;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Transport;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * Recipe 19-3: Attaching Files User: Freddy Updated: J. Juneau
 */
public class Recipe19_4 {

    public static void main(String[] args) {
        Recipe19_4 recipe = new Recipe19_4();
        recipe.start();
    }

    private void start() {
        String host = "smtp.gmail.com";
        String username = "mymailusername";
        String password = "mygmailpassword";
        String from = "mygmailusername@gmail.com";
        String to = "someuser@somewhere.com";
                
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user", username);
        properties.put("mail.smtp.password", password);


        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
                
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Subject Test");

            // Create Mime Content
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String html = "<H1>Important Message</H1>" +
                          "<b>This is an important message...</b>"+
                          "<br/><br/>" +
                          "<i>Be sure to code your Java today!</i>" +
                          "<H2>It is the right thing to do!</H2>";
            messageBodyPart.setContent(html, "text/html; charset=utf-8");

            MimeBodyPart fileBodyPart = new MimeBodyPart();
            fileBodyPart.attachFile("<path-to-attachment>/attach.txt");

            MimeBodyPart fileBodyPart2 = new MimeBodyPart();
            fileBodyPart2.attachFile("<path-to-attachment>/attach2.txt");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(fileBodyPart);
            //add another body part to supply another attachment
            multipart.addBodyPart(fileBodyPart2);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}
