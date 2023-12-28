package com.mailbox.service.impl;

import com.mailbox.config.MailProperties;
import com.mailbox.service.MailBoxService;
import com.mailbox.service.dto.MailPropertiesDTO;
import com.sun.mail.pop3.POP3Folder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Properties;
import javax.mail.*;

@Service
public class MailBoxServiceImpl implements MailBoxService {

    public void setMailUser(MailPropertiesDTO mailPropertiesDTO) {
        MailProperties mailProperties = new MailProperties();
        mailProperties.setPassword(mailPropertiesDTO.getPassword());
        mailProperties.setUsername(mailProperties.getUsername());
    }

    @Override
    public String mailControl(String username,String password) {
        Properties props = new Properties();
        props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.pop3.socketFactory.fallback", "false");
        props.put("mail.pop3.socketFactory.port", "995");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.host", "pop.gmail.com");
        props.put("mail.pop3.user", username);
        props.put("mail.store.protocol", "pop3");
        props.put("mail.pop3.ssl.protocols", "TLSv1.2");

        // 2. Creates a javax.mail.Authenticator object.
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getDefaultInstance(props, auth);

        try {
            // 4. Get the POP3 store provider and connect to the store.
            Store store = session.getStore("pop3");
            store.connect("pop.gmail.com", username, password);

            // 5. Get folder and open the INBOX folder in the store.
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                System.out.println(message.toString());
            }
            inbox.close(false);
            store.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return "deneme";
    }

    public void folderDownload() {
    }

    public void extractFolder() {

    }

    public void compareTable() {

    }
}
