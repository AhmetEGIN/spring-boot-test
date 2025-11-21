package com.egin.springboot.repository;

import com.egin.springboot.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/*
*
Spring-Boot @DataJpaTest annotation'unu repository alanını test etmemiz için sunar
* Gerçek veri tabanına bağlanmak zorunda değiliz. In-memory olarak test edebiliriz
* Repository, başka bir bağımlılığı içermediği için Mockito kullanmıyoruz
*
*  */
@DataJpaTest
public class EmployeeRepositoryTests {

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = Employee.builder()
                .firstName("Ahmet")
                .lastName("Egin")
                .email("ahmet@gmail.com")
                .build();
    }


    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit test for save employee operation
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ahmet")
//                .lastName("Egin")
//                .email("ahmet@gmail.com")
//                .build();
        // when - action or the behaviour that we are going test
        Employee savedEmployee = this.employeeRepository.save(employee);
        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo("Ahmet");
        assertThat(savedEmployee.getId()).isGreaterThan(0);



    }

    // JUnit test for get all employees operation
    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ahmet")
//                .lastName("Egin")
//                .email("ahmet@gmail.com")
//                .build();
        Employee employee2 = Employee.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john@gmail.com")
                .build();
        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        // when - action or the behaviour that we are going test
        List<Employee> savedEmployees = this.employeeRepository.findAll();

        // then - verify the output
        assertThat(savedEmployees.size()).isEqualTo(2);
        assertThat(savedEmployees.get(0)).isEqualTo(employee);


    }

    // JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployee_whenFindById_thenReturnEmployee(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ahmet")
//                .lastName("Egin")
//                .email("ahmet@gmail.com")
//                .build();
        this.employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = this.employeeRepository.findById(employee.getId()).orElse(null);
        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo("Ahmet");

    }

    // JUnit test for find by email operation
    @Test
    @DisplayName("JUnit test for find by email operation")
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployee(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ahmet")
//                .lastName("Egin")
//                .email("ahmet@gmail.com")
//                .build();
        this.employeeRepository.save(employee);
        // when - action or the behaviour that we are going test
        Employee savedEmployee = this.employeeRepository.findByEmail(employee.getEmail()).orElse(null);
        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getEmail()).isEqualTo("ahmet@gmail.com");
    }

    // JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ahmet")
//                .lastName("Egin")
//                .email("ahmet@gmail.com")
//                .build();
        this.employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = this.employeeRepository.findById(employee.getId()).orElse(null);
        savedEmployee.setFirstName("John");
        savedEmployee.setLastName("Smith");
        employeeRepository.save(savedEmployee);
        Employee updatedEmployee = this.employeeRepository.findById(employee.getId()).orElse(null);

        // then - verify the output
        assertThat(updatedEmployee.getFirstName()).isEqualTo("John");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Smith");

    }


    // JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployee_whenDelete_thenEmployeeIsRemoved(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ahmet")
//                .lastName("Egin")
//                .email("ahmet@gmail.com")
//                .build();
        this.employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        this.employeeRepository.deleteById(employee.getId());

        // then - verify the output
        assertThat(this.employeeRepository.findById(employee.getId())).isEmpty();
    }


    // JUnit test for custom query using JPQL with index parameters
    @DisplayName("JUnit test for custom query using JPQL with index parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployee(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ahmet")
//                .lastName("Egin")
//                .email("ahmet@gmail.com")
//                .build();
        this.employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = this.employeeRepository.findByJPQL("Ahmet", "Egin");
        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    // JUnit test for custom query using JPQL with named parameters
    @DisplayName("JUnit test for custom query using JPQL with named parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployee(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ahmet")
//                .lastName("Egin")
//                .email("ahmet@gmail.com")
//                .build();
        this.employeeRepository.save(employee);

        // when - action or the behaviour that we are going test
        Employee savedEmployee = this.employeeRepository.findByJPQLNamedParams("Ahmet", "Egin");
        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    // JUnit test for custom query using Native SQL with index parameters
    @DisplayName("JUnit test for custom query using Native SQL with index parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployee(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ahmet")
//                .lastName("Egin")
//                .email("ahmet@gmail.com")
//                .build();
        this.employeeRepository.save(employee);
        // when - action or the behaviour that we are going test
        Employee savedEmployee = this.employeeRepository.findByNativeSQL("Ahmet", "Egin");
        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    // JUnit test for custom query using Native SQL with named parameters
    @DisplayName("JUnit test for custom query using Native SQL with named parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamed_thenReturnEmployee(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ahmet")
//                .lastName("Egin")
//                .email("ahmet@gmail.com")
//                .build();
        this.employeeRepository.save(employee);
        // when - action or the behaviour that we are going test
        Employee savedEmployee = this.employeeRepository.findByNativeSQLNamed("Ahmet", "Egin");
        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }





}
