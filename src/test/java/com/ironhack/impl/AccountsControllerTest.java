package com.ironhack.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.controller.dto.TransferDTO;
import com.ironhack.model.AccountHolder;
import com.ironhack.model.Checking;
import com.ironhack.model.Money;
import com.ironhack.model.Status;
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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountsControllerTest {

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

    //Account class
    @Test
    void getAllAccounts() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("6"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hamzah Mejia"));

    }
    @Test
    void transferMoney() throws Exception {
        TransferDTO transferDTO = new TransferDTO("Hamzah Mejia","ironhack5",6,new BigDecimal("100"),3);

        String body = objectMapper.writeValueAsString(transferDTO);

        mockMvc.perform(patch("/transfer").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        //Hay que ajustar las cantidades para cada test
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1300"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("900"));

    }

    @Test
    void transferMoney_From_ThirdUser() throws Exception {
        TransferDTO transferDTO = new TransferDTO("Hamzah Mejia","ironhack5",new BigDecimal("100"),3);

        String body = objectMapper.writeValueAsString(transferDTO);

        mockMvc.perform(patch("/thirdpartyusers/ironhack").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        //Hay que ajustar las cantidades para cada test
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1800"));


    }
    //CheckingAccount class GET
    @Test
    void getCheckings() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/checkings"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Erin Carr"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hana Jennings"));

    }

    @Test
    void getMyAccountsByOwner() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/myaccounts/Erin Carr/ironhack"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Erin Carr"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("ironhack"));
    }
    //CheckingAccount class POST and DELETE
    @Test
    void saveChecking_deleteChecking_test() throws Exception {

        Optional<AccountHolder> primaryOwner = accountHolderRepository.findById("Hamzah Mejia");
        Money balance = new Money(new BigDecimal("1000"));
        Checking newChecking = new Checking(balance, primaryOwner.get(),null,null,"ironhack5",null,null, Status.ACTIVE);

        String body = objectMapper.writeValueAsString(newChecking);

        mockMvc.perform(post("/checkings").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/checkings"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();

        //corregir el ID de DELETE a cada test
        mockMvc.perform(delete("/checkings/17"))
                .andExpect(status().isNoContent())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    //CreditCard class
    @Test
    void getCreditCards() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/creditcards"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("3"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("4"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Elinor Mcgee"));

    }
    @Test
    void getCreditCardByOwner() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/mycreditaccount/3"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Elinor Mcgee"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1987-07-13"));
    }
    //Savings class
    @Test
    void getSavings() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/savings"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("5"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("6"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Luisa Foley"));

    }
    @Test
    void getSavingsByOwner() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/mysavingsaccount/5"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Luisa Foley"));
    }
}
