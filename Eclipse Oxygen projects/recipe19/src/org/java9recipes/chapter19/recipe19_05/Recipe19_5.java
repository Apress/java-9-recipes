package org.java9recipes.chapter19.recipe19_05;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.java9recipes.chapter19.MessageAuthenticator;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Recipe 19-5:  Sending to a group
 * User: Freddy
 * Updated: J. Juneau
 */
public class Recipe19_5 {
    public static void main(String[] args) {
        Recipe19_5 recipe = new Recipe19_5();
        recipe.start();
    }

    private void start() {

        List<String> emails = getEmails();
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.somewhere.com");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties, new MessageAuthenticator("username","password"));

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("someone@somewhere.com"));
            message.setRecipients(Message.RecipientType.BCC, getRecipients(emails));
            message.setSubject("Subject");
            message.setContent("This is a test message", "text/plain");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Address[] getRecipients(List<String> emails) throws AddressException {
        Address[] addresses = new Address[emails.size()];
        for (int i =0;i < emails.size();i++) {
            addresses[i] = new InternetAddress(emails.get(i));
        }
        return addresses;
    }

    public List<String> getEmails() {
        ArrayList<String> emails = new ArrayList<>();
        emails.add("jack@hill.com");
        emails.add("jill@hill.com");
        emails.add("water@hill.com");
        return emails;
    }

   
}
