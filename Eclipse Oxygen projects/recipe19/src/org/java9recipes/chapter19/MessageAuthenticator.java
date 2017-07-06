package org.java9recipes.chapter19;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MessageAuthenticator extends Authenticator {
    PasswordAuthentication authentication = null;

    public MessageAuthenticator(String username, String password) {
        authentication = new PasswordAuthentication(username,password);
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return authentication;
    }
}