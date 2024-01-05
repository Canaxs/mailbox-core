package com.mailbox.service.impl;

import com.mailbox.common.MailConstants;
import com.mailbox.enums.MailType;
import com.mailbox.models.response.MailInfoResponse;
import com.mailbox.persistence.entity.User;
import com.mailbox.persistence.repository.MailsRepository;
import com.mailbox.service.FileService;
import com.mailbox.service.MailBoxService;
import com.mailbox.service.UserService;
import com.mailbox.service.dto.MailInfoDTO;
import com.mailbox.service.dto.UserDto;
import com.mailbox.service.mapper.MailMapper;
import com.mailbox.service.mapper.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

@Service
public class MailBoxServiceImpl implements MailBoxService {

    private final UserService userService;

    private final UserMapper userMapper;

    private final MailMapper mailMapper;

    private final MailsRepository mailsRepository;

    private final FileService fileService;

    public MailBoxServiceImpl(UserService userService, UserMapper userMapper
            , MailMapper mailMapper, MailsRepository mailsRepository, FileService fileService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.mailMapper = mailMapper;
        this.mailsRepository = mailsRepository;
        this.fileService = fileService;
    }
    public User securityUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getByUsername(username).get();
    }

    @Override
    public List<MailInfoResponse> mailControl() {
        List<MailInfoResponse> mailInfoResponses = new ArrayList<>();
        User user = securityUser();
        try {
            Session session = sessionCreate(user);
            // 4. Get the POP3 store provider and connect to the store.
            Store store = session.getStore("pop3");
            store.connect("pop.gmail.com", user.getMailAddress(), user.getMailPassword());

            // 5. Get folder and open the INBOX folder in the store.
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                mailInfoResponses.add(messageExtract(message));
            }
            inbox.close(false);
            store.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return mailInfoResponses;
    }
    public Session sessionCreate(User user) {
        Properties props = new Properties();
        props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.pop3.socketFactory.fallback", "false");
        props.put("mail.pop3.socketFactory.port", "995");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.host", "pop.gmail.com");
        props.put("mail.pop3.user", user.getMailAddress());
        props.put("mail.store.protocol", "pop3");
        props.put("mail.pop3.ssl.protocols", "TLSv1.2");

        // 2. Creates a javax.mail.Authenticator object.
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user.getMailAddress(), user.getMailPassword());
            }
        };

        return Session.getDefaultInstance(props, auth);
    }

    public MailInfoResponse messageExtract(Message message) throws IOException, MessagingException {

        String content = messageContentExtract(message.getContent());
        MailType mailtype = textControl(message.getSubject(),content,message.getFrom());
        InternetAddress ia = (InternetAddress) message.getFrom()[0];

        return MailInfoResponse.builder()
                .mailTitle(message.getSubject())
                .mailSubject(content)
                .mailType(mailtype.toString())
                .mailSender(ia.getAddress())
                .mailSendDate(message.getSentDate())
                .build();
    }

    public String messageContentExtract(Object content) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        MimeMultipart mimeMultipart = (MimeMultipart) content;
        for (int i = 0; i < mimeMultipart.getCount(); i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                return result + " " + bodyPart.getContent();
            }
            result.append(this.parseBodyPart(bodyPart));
        }
        return result.toString();
    }
    private String parseBodyPart(BodyPart bodyPart) throws MessagingException, IOException {
        if (bodyPart.isMimeType("text/html")) {
            return " " + org.jsoup.Jsoup
                    .parse(bodyPart.getContent().toString())
                    .text();
        }
        if (bodyPart.getContent() instanceof MimeMultipart){
            return messageContentExtract((MimeMultipart)bodyPart.getContent());
        }
        return "";
    }

    public MailType addressControl(Address[] from) {
        InternetAddress ia = (InternetAddress) from[0];
        String addressName= ia.getAddress();
        return switch (addressName) {
            case MailConstants.socialTwitterAddress, MailConstants.socialFacebookAddress, MailConstants.socialLinkedinAddress, MailConstants.socialLinkedinAddress2, MailConstants.socialInstagramAddress ->
                    MailType.SOCIAL;
            case MailConstants.musicAppleMusic, MailConstants.musicSpotifyMusic -> MailType.MUSIC;
            default -> MailType.UNKNOWN;
        };
    }
    public MailType textControl(String title,String content,Address[] from) {
        Map<String, MailType> mailTypeStringMap = fileService.fileReadConvertList();
        if(!title.isEmpty()) {
            for (Map.Entry<String, MailType> entry : mailTypeStringMap.entrySet()) {
                if(title.toUpperCase().contains(entry.getKey().toUpperCase())) {
                    return entry.getValue();
                }
            }
        }
        if (!content.isEmpty()) {
            for (Map.Entry<String, MailType> entry : mailTypeStringMap.entrySet()) {
                if(content.toUpperCase().contains(entry.getKey().toUpperCase())) {
                    return entry.getValue();
                }
            }
        }

        return addressControl(from);
    }

}
