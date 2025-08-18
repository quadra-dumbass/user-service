package com.quadra.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureMockMvc
public class OAuth2Test {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void oauthLogin() throws Exception {
        mockMvc.perform(get("/oauth2/authorization/google")
                        .with(oauth2Login().attributes(attrs -> {
                            attrs.put("sub", "51tass5ag");
                            attrs.put("name", "rymph");
                            attrs.put("email", "testCode@gmail.com");
                        })))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().exists("Set-Cookie"))
                .andDo(result -> {
                    String setCookie = result.getResponse().getHeader("Set-Cookie");
                    System.out.println("Set-Cookie: " + setCookie);
                });
    }
}
