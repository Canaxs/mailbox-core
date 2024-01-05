package com.mailbox.service.mapper;

import com.mailbox.persistence.entity.Mails;
import com.mailbox.service.dto.MailInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MailMapper {

    MailMapper INSTANCE = Mappers.getMapper( MailMapper.class );

    Mails toMails(MailInfoDTO mailInfoDTO);
}
