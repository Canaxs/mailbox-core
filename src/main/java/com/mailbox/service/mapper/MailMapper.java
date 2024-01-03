package com.mailbox.service.mapper;

import com.mailbox.persistence.entity.Mails;
import com.mailbox.service.dto.MailInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MailMapper {
    Mails toMails(MailInfoDTO mailInfoDTO);
}
