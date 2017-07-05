package org.java9recipes.chapter19.recipe19_03;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.java9recipes.chapter19.MessageAuthenticator;

import java.io.IOException;
import java.util.Properties;

/**
 * Recipe 19-3:  Attaching Files
 * User: Freddy
 * Updated: J. Juneau
 */
public class Recipe19_3 {

    public static void main(String[] args) {
        Recipe19_3 recipe = new Recipe19_3();
        recipe.start();
    }

    private void start() {
        String host = "smtp.somewhere.com";
        String username = "username";
        String password = "password";
        String from = "someone@somewhere.com";
        String to = "anotherone@somewhere.com";



        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);

        // No authentication is required to send
        //Session session = Session.getDefaultInstance(properties, null);
        Session session = Session.getDefaultInstance(properties, new MessageAuthenticator(username,password));
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Subject Test");
            
            // Create Mime Content
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("This is a test message", "text/plain");

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
