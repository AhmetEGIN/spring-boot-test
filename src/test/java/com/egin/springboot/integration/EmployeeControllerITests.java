package com.egin.springboot.integration;

import com.egin.springboot.model.Employee;
import com.egin.springboot.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerITests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        employeeRepository.deleteAll();
    }

    @Test
    public void givenEmployee_whenCreateEmployee_thenReturnCreatedEmployee() throws Exception {
        // given - preconditions or setup
        Employee employee = Employee.builder()
                .firstName("Ahmet")
                .lastName("Egin")
                .email("ahmet@gmail.com")
                .build();

        //when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(employee)));

        // then - verify the output using assertions
        response
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(employee.getEmail())));
    }


    @Test
    public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeesList() throws Exception {
        // given - precondition or setup
        List<Employee> listOfEmployees = List.of(
                Employee.builder().firstName("Ahmet").lastName("Egin").email("ahmet@gmail.com").build(),
                Employee.builder().firstName("John").lastName("Doe").email("john@gmail.com").build()
        );
        employeeRepository.saveAll(listOfEmployees);
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"));

        // then - verify the output
        response
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
                        CoreMatchers.is(listOfEmployees.size())));
    }


    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployee() throws Exception {
        // given - precondition or setup
        long employeeId = 1L;
        Employee employee = Employee.builder()
                .firstName("Ahmet")
                .lastName("Egin")
                .email("ahmet@gmail.com")
                .build();

        employeeRepository.save(employee);
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employee.getId()));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(employee.getFirstName())));

    }

    @Test
    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception {
        // given - precondition or setup
        long employeeId = 1L;
        Employee employee = Employee.builder()
                .firstName("Ahmet")
                .lastName("Egin")
                .email("ahmet@gmail.com")
                .build();
        employeeRepository.save(employee);
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employeeId));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
        ;

    }

    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() throws Exception {
        // given - precondition or setup
        Employee savedEmployee = Employee.builder()
                .firstName("Ahmet")
                .lastName("Egin")
                .email("ahmet@gmail.com")
                .build();
        employeeRepository.save(savedEmployee);
        Employee updatedEmployee = Employee.builder()
                .firstName("Mehmet")
                .lastName("Engin")
                .email("mehmet@gmail.com")
                .build();



        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", savedEmployee.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(updatedEmployee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(updatedEmployee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(updatedEmployee.getEmail())));


    }


    // JUnit test for update employee - negative scenario
    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturn404() throws Exception {
        // given - precondition or setup
        long employeeId = 1L;
        Employee savedEmployee = Employee.builder()
                .firstName("Ahmet")
                .lastName("Egin")
                .email("ahmet@gmail.com")
                .build();
        employeeRepository.save(savedEmployee);
        Employee updatedEmployee = Employee.builder()
                .firstName("Mehmet")
                .lastName("Engin")
                .email("mehmet@gmail.com")
                .build();

        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception {
        // given - precondition or setup
        long employeeId = 1L;
        Employee savedEmployee = Employee.builder()
                .firstName("Ahmet")
                .lastName("Egin")
                .email("ahmet@gmail.com")
                .build();
        employeeRepository.save(savedEmployee);
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", savedEmployee.getId()));
        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }



}
