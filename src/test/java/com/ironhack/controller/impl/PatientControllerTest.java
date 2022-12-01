package com.ironhack.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.model.Employee;
import com.ironhack.model.Patient;
import com.ironhack.model.Status;
import com.ironhack.repository.EmployeeRepository;
import com.ironhack.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PatientControllerTest {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void getPatients() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julia Dusterdieck"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("761527"));

    }

    @Test
    void getPatientById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/patients/3"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Julia Dusterdieck"));
    }


    @Test
    void getPatientsWithDocStatusOff() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/patients/admittedby-status"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("John Paul Armes"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("German Ruiz"));


    }

    @Test
    void getPatientsWithDepartment() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/patients/department?department=immunology"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Sam Ortega"));



    }

    @Test
    void savePatient() throws Exception {

        LocalDate date1 = LocalDate.of(1999,2,21);
        Date date = Date.from(date1.atStartOfDay(ZoneId.systemDefault()
        ).toInstant());
        Optional<Employee> employeeOptional = employeeRepository.findById(356712);
        Patient patient = new Patient(7,"paciente0",date,employeeOptional.get());
        String body = objectMapper.writeValueAsString(patient);

        mockMvc.perform(post("/patients").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        //hace un get y mira si ha creado
        MvcResult mvcResult = mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
//        assertTrue(mvcResult.getResponse().getContentAsString().contains("156545"));

        mockMvc.perform(delete("/patients/7"))
                .andExpect(status().isNoContent())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    void updatePatient() throws Exception {
        LocalDate date1 = LocalDate.of(1972,1,12);
        Date date = Date.from(date1.atStartOfDay(ZoneId.systemDefault()
        ).toInstant());

        Optional<Employee> employeeOptional = employeeRepository.findById(356712);
        Patient patient = new Patient(2,"Marian Garcia",date,employeeOptional.get());
        String body = objectMapper.writeValueAsString(patient);

        mockMvc.perform(put("/patients/2").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Marian Garcia"));


    }
}