package org.java9recipes.chapter19.recipe19_06;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

/**
 * Recipe 19-6:  Checking Email
 * User: Freddy
 * Updated: J. Juneau
 */
public class Recipe19_6 {

    public static void main(String[] args) {
        Recipe19_6 recipe = new Recipe19_6();
        recipe.start();
    }

    private void start() {

        Properties properties = new Properties();
        String username = "username";
        String password = "password";
        String folder = "Inbox";
        String host = "imap.host.com";

        try {
            Session session = Session.getDefaultInstance(properties, null);
            Store store = session.getStore("imap");
            store.connect(host,username,password);
            System.out.println(store);
            Folder inbox = store.getFolder(folder);
            inbox.open(Folder.READ_WRITE);
            int messageCount = inbox.getMessageCount();
            int startMessage = messageCount - 10;
            if (startMessage< 1) startMessage =1;
            Message messages[]  = inbox.getMessages(startMessage, messageCount);
            for (Message message : messages) {
                boolean hasBeenRead = false;
                for (Flags.Flag flag : message.getFlags().getSystemFlags()) {
                    if (flag == Flags.Flag.SEEN) {
                        hasBeenRead = true;
                        break;
                    }
                }
                message.setFlag(Flags.Flag.SEEN, false);
                System.out.println(message.getSubject() + " "+ (hasBeenRead? "(read)" : "") + message.getContent());

            }
            inbox.close(true);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}
