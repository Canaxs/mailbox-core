package com.mailbox.service.mapper;

import com.mailbox.models.response.UserCreateResponse;
import com.mailbox.persistence.entity.User;
import com.mailbox.service.dto.MailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserCreateResponse toUserCreateResponse(User user);
    
    MailDTO toMailDTO(User user);

}
