package com.ironhack.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ironhack.controller.dto.BalanceDTO;
import com.ironhack.model.*;
import com.ironhack.repository.AccountHolderRepository;
import com.ironhack.repository.AccountRepository;
import com.ironhack.repository.CheckingRepository;
import com.ironhack.service.impl.CheckingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {
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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper.registerModule(new JavaTimeModule());

    }
    //GET
    @Test
    void getAllAccountHolders() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accountholders"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("6"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Naomi Barr"));

    }
    @Test
    void getAllUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("UsuarioExterno3"));

    }
    @Test
    void getAllAdmins() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admins"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Administrador2"));

    }
    @Test
    void getAllThirdPartyUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/thirdpartyusers"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("3"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("UsuarioExterno2"));

    }

    //POST DELETE Users

    @Test
    void saveAccountHolder_deleteAccountHolder_test() throws Exception {

       LocalDate localDate = LocalDate.of(1995,12,19);
        Address primaryAddress = new Address("Barcelona","Calle Valencia","3");
       AccountHolder accountHolder = new AccountHolder("Juan Rodriguez","ironhack20","USER",localDate,primaryAddress);

        String body = objectMapper.writeValueAsString(accountHolder);

        mockMvc.perform(post("/accountholders").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/accountholders"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();

        mockMvc.perform(delete("/accountholders/Juan Rodriguez"))
                .andExpect(status().isNoContent())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

    }
    @Test
    void saveAdmin_deleteAdmin_test() throws Exception {
        Admin admin = new Admin("Juan Rodriguez","Administrador20","ADMIN");

        String body = objectMapper.writeValueAsString(admin);

        mockMvc.perform(post("/admins").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/admins"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();

        mockMvc.perform(delete("/admins/Juan Rodriguez"))
                .andExpect(status().isNoContent())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

    }
    @Test
    void saveThirdPartyUser_deleteThirdPartyUser_test() throws Exception {

        ThirdParty thirdParty = new ThirdParty("Juan Rodriguez","UsuarioExterno20","CONTRIBUTOR","root");
        String body = objectMapper.writeValueAsString(thirdParty);

        mockMvc.perform(post("/thirdpartyusers").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/thirdpartyusers"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();

        mockMvc.perform(delete("/thirdpartyusers/Juan Rodriguez"))
                .andExpect(status().isNoContent())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    //PATCH
    @Test
    void updateBalanceAccount() throws Exception {

        BalanceDTO balanceDTO = new BalanceDTO(new BigDecimal(1300));
        String body = objectMapper.writeValueAsString(balanceDTO);

        mockMvc.perform(patch("/accounts/balance/6").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        //hace un get y mira si ha creado
        MvcResult mvcResult = mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1300"));
    }

}
