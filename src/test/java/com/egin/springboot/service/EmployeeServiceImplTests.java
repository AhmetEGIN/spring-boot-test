package com.egin.springboot.service;

import com.egin.springboot.exception.ResourceNotFoundException;
import com.egin.springboot.model.Employee;
import com.egin.springboot.repository.EmployeeRepository;
import com.egin.springboot.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

// @Mock ve @InjecyMocks anotasyonlarını kullanabilmek için MockitoExtension ekliyoruz
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTests {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
//        employeeRepository = Mockito.mock(EmployeeRepository.class);
//        employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder()
                .id(1L)
                .firstName("Ahmet")
                .lastName("Egin")
                .email("ahmet@gmail.com")
                .build();
    }

    // JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployee_whenSaveEmployee_thenReturnEmployee(){
        // given - precondition or setup
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);
        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo(savedEmployee.getFirstName());

    }

    // JUnit test for saveEmployee method which throws exception
    @DisplayName("JUnit test for saveEmployee method which throws exception")
    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowResourceNotFoundException(){
        // given - precondition or setup
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        // Zaten hata alacağımız için bu satıra gerek yok. save metodu çağrılmayacak.
//        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);
        // when - action or the behaviour that we are going test
        Assertions.assertThrows(ResourceNotFoundException.class, () -> employeeService.saveEmployee(employee));
        // then - verify the output
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    // JUnit test for getAllEmployees method
    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Mehmet")
                .lastName("Yilmaz")
                .email("mehmet@gmail.com")
                .build();
        BDDMockito.given(employeeRepository.findAll())
                .willReturn(java.util.List.of(employee, employee1));

        // when - action or the behaviour that we are going test
        List<Employee> employees = employeeService.getAllEmployees();

        // then - verify the output
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(2);
    }

    // JUnit test for getAllEmployees method (negative case)
    @DisplayName("JUnit test for getAllEmployees method (negative case)")
    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList(){
        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Mehmet")
                .lastName("Yilmaz")
                .email("mehmet@gmail.com")
                .build();
        BDDMockito.given(employeeRepository.findAll())
                .willReturn(Collections.emptyList());

        // when - action or the behaviour that we are going test
        List<Employee> employees = employeeService.getAllEmployees();

        // then - verify the output
        assertThat(employees).isEmpty();
        assertThat(employees.size()).isEqualTo(0);
    }

    // JUnit test for getEmployeeById method
    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployee(){
        // given - precondition or setup
        BDDMockito.given(employeeRepository.findById(1L))
                .willReturn(Optional.of(employee));

        // when - action or the behaviour that we are going test
        Employee savedEmployee = employeeService.getEmployeeById(employee.getId()).get();
        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo(employee.getFirstName());

    }


    // JUnit test for updateEmployee method
    @DisplayName("JUnit test for updateEmployee method")
    @Test
    public void givenEmployee_whenUpdateEmployee_thenReturnEmployee(){
        // given - precondition or setup
        BDDMockito.given(employeeRepository.save(employee))
                .willReturn(employee);
        employee.setEmail("egin@gmail.com");
        // when - action or the behaviour that we are going test
        Employee updatedEmployee = this.employeeService.updateEmployee(employee);
        // then - verify the output
        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getEmail()).isEqualTo("egin@gmail.com");

    }


    // JUnit test for deleteEmployee method
    @DisplayName("JUnit test for deleteEmployee method")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenNothing(){
        // given - precondition or setup
        BDDMockito.willDoNothing().given(employeeRepository).deleteById(1L);

        // when - action or the behaviour that we are going test
        employeeService.deleteEmployee(1L);
        // then - verify the output
        verify(employeeRepository, Mockito.times(1)).deleteById(1L);


    }

}
