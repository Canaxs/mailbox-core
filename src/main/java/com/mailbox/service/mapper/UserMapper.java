package com.mailbox.service.mapper;

import com.mailbox.models.response.UserCreateResponse;
import com.mailbox.persistence.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserCreateResponse toUserCreateResponse(User user);

}
