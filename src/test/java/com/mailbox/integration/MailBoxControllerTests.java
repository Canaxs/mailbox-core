package com.mailbox.integration;

import com.mailbox.common.CommonConstants;
import com.mailbox.models.request.UserAuthRequest;
import com.mailbox.service.AuthenticationService;
import com.mailbox.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("dev")
@AutoConfigureMockMvc(addFilters = false)
public class MailBoxControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    /*
    public String userToken() {
        return jwtService.generateToken("mero");
    }

    @Test
    public void testMailControl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CommonConstants.mailBoxControllerControlURL)
                        .header("Authorization", userToken())
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
     */
}
