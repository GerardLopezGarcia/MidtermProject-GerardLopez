package com.ironhack.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.controller.dto.EmployeeDepartmentDTO;
import com.ironhack.model.Employee;
import com.ironhack.model.Status;
import com.ironhack.repository.EmployeeRepository;
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

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EmployeeControllerTest {

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
    void getEmployees() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Paolo Rodriguez"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("pulmonary"));

    }

    @Test
    void getEmployeeById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employees/employee_id?employee_id=156545"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("156545"));
    }

    @Test
    void getEmployeesByStatus() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employees/status?status=ON"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("ON"));
    }

    @Test
    void getEmployeesByDepartment() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employees/department?department=orthopaedic"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("orthopaedic"));
    }

    @Test
    void saveEmployee() throws Exception {
        Employee employee = new Employee(190045,"orthopaedic","Juan Benitez", Status.ON_CALL);
        String body = objectMapper.writeValueAsString(employee);

        mockMvc.perform(post("/employees").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        //hace un get y mira si ha creado
        MvcResult mvcResult = mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
//        assertTrue(mvcResult.getResponse().getContentAsString().contains("156545"));

        mockMvc.perform(delete("/employees/190045"))
                .andExpect(status().isNoContent())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    void updateEmployeeStatus() throws Exception {
        Employee employee = new Employee(166552,"pulmonary","Maria Lin", Status.ON_CALL);
        String body = objectMapper.writeValueAsString(employee);

        mockMvc.perform(patch("/employees/status/166552").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        //hace un get y mira si ha creado
        MvcResult mvcResult = mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Maria Lin"));
    }

    @Test
    void updateEmployeeDepartment() throws Exception {
        EmployeeDepartmentDTO employeeDepartmentDTO = new EmployeeDepartmentDTO("cardiology");
        String body = objectMapper.writeValueAsString(employeeDepartmentDTO);

        mockMvc.perform(patch("/employees/department/166552").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        //hace un get y mira si ha creado
        MvcResult mvcResult = mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType((MediaType.APPLICATION_JSON)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("cardiology"));
    }
}