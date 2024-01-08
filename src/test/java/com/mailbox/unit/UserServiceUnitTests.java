package com.mailbox.unit;

import com.mailbox.models.request.UserCreateRequest;
import com.mailbox.persistence.entity.User;
import com.mailbox.persistence.repository.UserRepository;
import com.mailbox.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void unitTestCreateUser() {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setUsername("merounit");
        userCreateRequest.setPassword("12345");
        userCreateRequest.setMailAddress("mailboxspring@gmail.com");
        userCreateRequest.setMailPassword("ptqn cqmd iuik tkcp");

        User user = User.builder()
                .Id(6L)
                .username(userCreateRequest.getUsername())
                        .password(userCreateRequest.getPassword())
                                .mailAddress(userCreateRequest.getMailAddress())
                                        .mailPassword(userCreateRequest.getPassword()).build();

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User userTest = userService.createUser(userCreateRequest);

        Assertions.assertThat(userTest).isNotNull();

    }

}
