package com.ironhack.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ironhack.repository.AccountHolderRepository;
import com.ironhack.repository.AccountRepository;
import com.ironhack.repository.CheckingRepository;
import com.ironhack.service.impl.CheckingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
public class TestingSecurity {
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CheckingService checkingService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();



    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        objectMapper.registerModule(new JavaTimeModule());

    }

    //Test sin auth
    @Test
    void getAllAccounts() throws Exception {
         mockMvc.perform(get("/accounts")).andExpect(status().isUnauthorized());
    }

    //Test con seguridad
    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllAccounts_whithAuth() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("6"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hamzah Mejia"));

    }

    //con Auth no funciona
    @Test
    @WithMockUser(roles = "USER",username = "Erin Carr")
    void getMyAccountsByOwner_withAuth() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/myaccounts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Erin Carr"));

    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void getCheckings() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/checkings"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Erin Carr"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hana Jennings"));

    }

    @Test
    @WithMockUser(roles = "USER",username = "Hamzah Mejia")
    void getMyAccountsByOwner_withBadAuth() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/myaccounts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Erin Carr"));

    }

    @Test
    void getMyAccountsByOwner_withAuth_perform() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/myaccounts").with(httpBasic("Erin Carr","ironhack")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Erin Carr"));

    }
    @Test
    void getMyAccountsByOwner_withBadAuth_perform() throws Exception {
        mockMvc.perform(get("/myaccounts").with(httpBasic("Erin Carr","ironhack5"))).andExpect(status().isUnauthorized());
    }



}
