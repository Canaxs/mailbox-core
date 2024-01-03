package com.mailbox.service.impl;

import com.mailbox.config.MailProperties;
import com.mailbox.persistence.entity.Mails;
import com.mailbox.persistence.entity.User;
import com.mailbox.persistence.repository.MailsRepository;
import com.mailbox.service.MailBoxService;
import com.mailbox.service.UserService;
import com.mailbox.service.dto.MailDTO;
import com.mailbox.service.dto.MailInfoDTO;
import com.mailbox.service.mapper.MailMapper;
import com.mailbox.service.mapper.UserMapper;
import com.sun.mail.pop3.POP3Folder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Properties;
import javax.mail.*;

@Service
public class MailBoxServiceImpl implements MailBoxService {

    private final UserService userService;

    private final UserMapper userMapper;

    private final MailMapper mailMapper;

    private final MailsRepository mailsRepository;

    public MailBoxServiceImpl(UserService userService,UserMapper userMapper
            ,MailMapper mailMapper,MailsRepository mailsRepository) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.mailMapper = mailMapper;
        this.mailsRepository = mailsRepository;
    }
    public User securityUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getByUsername(username).get();
    }

    @Override
    public String mailControl() {
        MailDTO mailDTO = userMapper.toMailDTO(securityUser());

        try {
            Session session = sessionCreate(mailDTO);
            // 4. Get the POP3 store provider and connect to the store.
            Store store = session.getStore("pop3");
            store.connect("pop.gmail.com", mailDTO.getMailAddress(), mailDTO.getMailPassword());

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
    public Session sessionCreate(MailDTO mailDTO) {
        Properties props = new Properties();
        props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.pop3.socketFactory.fallback", "false");
        props.put("mail.pop3.socketFactory.port", "995");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.host", "pop.gmail.com");
        props.put("mail.pop3.user", mailDTO.getMailAddress());
        props.put("mail.store.protocol", "pop3");
        props.put("mail.pop3.ssl.protocols", "TLSv1.2");

        // 2. Creates a javax.mail.Authenticator object.
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailDTO.getMailAddress(), mailDTO.getMailPassword());
            }
        };

        return Session.getDefaultInstance(props, auth);
    }

    public void saveMail(MailInfoDTO mailInfoDTO) {
        Mails mails = mailMapper.toMails(mailInfoDTO);
        mails.setUserId(securityUser().getId());
        mailsRepository.save(mails);
    }

    public void folderDownload() {
    }

    public void extractFolder() {

    }

    public void compareTable() {

    }
}
