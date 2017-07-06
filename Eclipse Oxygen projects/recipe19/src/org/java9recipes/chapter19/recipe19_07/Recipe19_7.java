package org.java9recipes.chapter19.recipe19_07;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.Arrays;
import java.util.Properties;

/**
 * Recipe 19-7:  Monitoring an Email Account
 * User: Freddy
 * Updated: J. Juneau
 */
public class Recipe19_7 {
    volatile boolean shouldContinue = true;
    Thread mailCheckingThread = null;
    public static void main(String[] args) {
        Recipe19_7 recipe = new Recipe19_7();
        recipe.start();
    }

    public void start() {
        mailCheckingThread = new Thread(() -> {
            while (shouldContinue) {
                checkForMail();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        mailCheckingThread.start();

    }

    public void stop() {
        shouldContinue = false;
        mailCheckingThread.interrupt();
        try {
            mailCheckingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkForMail() {

        System.out.println("Checking for mail");

        Properties properties = new Properties();
        String username = "java9recipes";
        String password = "java9test";
        String folder = "Inbox";
        String host = "imap.gmail.com";

        try {
            Session session = Session.getDefaultInstance(properties, null);
            Store store = session.getStore("imaps");
            store.connect(host, username, password);
            Folder inbox = store.getFolder(folder);
            inbox.open(Folder.READ_WRITE);
            int messageCount = inbox.getMessageCount();
            Message messages[]  = inbox.getMessages(1,messageCount);
            for (Message message : messages) {
               // boolean hasBeenRead = false;
                if (Arrays.asList(message.getFlags().getSystemFlags()).contains(Flags.Flag.SEEN)) {
                    continue;                     // not interested in "seen" messages
                }
                if (processMessage(message)) {
                    System.out.println("Processed :"+message.getSubject());
                    message.setFlag(Flags.Flag.DELETED, true);
                } else {
                    System.out.println("Couldn't Understand :"+message.getSubject());
                    // set it as seen, but keep it around
                    message.setFlag(Flags.Flag.SEEN, true);
                }
            }
            inbox.close(true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private boolean processMessage(Message message) throws MessagingException {
        boolean result = false;

        String subject = message.getSubject().toLowerCase();
        if (subject.startsWith("subscribe")) {
            String emailAddress = extractEmail(message.getFrom());
            if (emailAddress != null) {
                subscribeToList(emailAddress);
                result = true;
            }
            
        } else if (subject.startsWith("unsubscribe")) {
            String emailAddress = extractEmail(message.getFrom());
            if (emailAddress != null) {
                unSubscribeToList(emailAddress);
                result = true;
            }
        }
        
        return result;
    }


    private String extractEmail(Address[] addressArray) {
        if ((addressArray == null) || (addressArray.length < 1)) return null;
        if (!(addressArray[0] instanceof InternetAddress)) return null;
        InternetAddress internetAddress = (InternetAddress) addressArray[0];
        return internetAddress.getAddress();
    }

    private void subscribeToList(String address) {
        System.out.println("Subscribing for :"+address);
    }

    private void unSubscribeToList(String address) {
        System.out.println("UNsubscribing for :"+address);
    }
    
}
