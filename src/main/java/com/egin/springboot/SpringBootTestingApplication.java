package com.egin.springboot;

import com.egin.springboot.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestingApplication.class, args);
//
//        Student student = new Student(1, "Ahmet");
//        System.out.println(student.getName());
//        System.out.println(student.getId());
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Smith");
    }

}
