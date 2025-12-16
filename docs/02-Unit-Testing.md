
# What is Unit Testing?
* Unit testing involves the testing of each unit or an individual component of the software application
* The purpose is to validate that each unit of the software code performs as expected
* Unit testing is done during development (coding phase) of an application by the developers
* Unit may be an individual function, method, procedure, module and object

* In Java, JUnit framework is used for unit testing Java applications
* Most of the times one component will depend on other component(s), so while implementing unit tests we should mock the dependencies with the desired behaviour using frameworks like Mockito


## Integration Testing
* As the name suggests, integration tests focus on integrating different layers of the application
* Basically, we write integration tests for testing a feature which may involve interaction with multiple components

## Unit Test Case Naming Convention
The test names should be insightful, and users should understand the behaviour and expectation of the test by just glancing name itself.

Given/when/then BDD style

givenEmployeeObject_whenSaveEmployee_thenReturnSavedEmployee

## Appropriate Assertions
Always use proper assertions to verify the expected vs. actual results. We should use various methods available in the Assert class of JUnit or similar frameworks like AssertJ.

## Mock External Services
* Although unit tests concentrate on specific and smaller pieces of code, there is a chance that the code is dependent on external services for some logic
* Therefore, we should mock the external services and merely test the logic and execution of our code for varying scenarios
* We can use various framework like **Mockito**, **EasyMock**, **JMockit** for mocking external services

---
## Spring boot starter test dependency
The Spring Boot Starter Test dependency is a primary dependency for testing the Spring Boot Applications. It holds all the necessary elements required for the testing.
This starter includes:
* Spring-specific dependencies
* Dependencies for auto-configuration
* Set of testing libraries - JUnit, Mockito, Hamcrest, AssertJ, JSONassert and JsonPath

---
## @DataJpaTest Annotation
* Spring-Boot provides the @DataJpaTest annotation to test the persistence layer components that will autoconfigure in-memory embedded database for testing purposes.
* The @DataJpaTest annotation doesn't load other Spring beans (@Components, @Controller, @Service and annotated beans) into ApplicationContext.
* By default, it scans for @Entity classes and configures Spring Data JPA repositories annotated with @Repository annotation.
* By default, tests annotated with @DataJpaTest are transactional and roll back at the end of each test.

---
# Mocking and Mockito
## What is Mocking?
* Mocking means replacing real dependencies with fake versions during unit testing. These mock objects simulate the behavior of real objects in a controlled way.

## What is Mockito?
* Mockito is a mocking framework for Java that allows developers to create and configure mock objects for unit testing.
## What is Stubbing in Mockito?
* Stubbing means telling your mock object what to return when a specific method is called

## What is the difference between JUnit and Mockito?
* JUnit is a testing framework used to write and run tests in Java, while Mockito is a mocking framework used to create mock objects for unit testing.
* JUnit provides the structure and tools to write and execute tests, while Mockito provides the ability to create mock objects and define their behavior for testing purposes.

## Mocking Dependencies using Mockito
* **Mockito mock() method**: We can use Mockito class mock() method to create a mock object of a given class or interface. This is the simplest way to mock an object.
* **Mockito @Mock Annotation**: We can mock an object using @Mock annotation too. It's useful when we want to use the mocked object at multiple places because we avoid calling mock() method multiple times. The code becomes more readable and we can specify mock object name that will be useful in case of errors.

## Mockito @InjectMocks Annotation
When we want to inject a mocked object into another mocked object, we can use @InjectMocks annotation. @InjectMock creates the mock object of the class and injects the mocks that are marked with the annotations @Mock into it.

---
## Hamcrest library
* **Hamcrest** is the well-known framework used for unit testing in Java ecosystem. It's bundled in JUnit and simply put, it uses existing predicates - called matcher classes - for making assertions.
* **Hamcrest** is commonly used with JUnit and other testing frameworks for making assertions. Specifically, instead of using JUnit's numerous assert methods, we only use the API's single assertThat statement with appropriate matchers.
* **Hamcrest is() method**: If we want to verify that the expected value (or object) is equal to the actual value (or object), we have to create our Hamcrest matcher by invoking the is() method of the Matchers class.
Syntax:
```java
assertThat(actualValue, is(expectedValue));
```

## @WebMvcTest Annotation
Spring Boot provides the @WebMvcTest annotation to test the Spring MVC Controllers. Also, @WebMvcTest based test runs faster as it will load only the specified controller and its dependencies only without loading the entire application.
Spring Boot instantiates only the web layer rather than the whole context. In an application with multiple controllers, you can even ask for only one to be instantiated by using, for example, @WebMvcTest(HomeController.class).

## @WebMvcTest vs @SpringBootTest
Spring Boot provides @WebMvcTest annotation to test Spring MVC controllers. This annotation creates an application context that contains all the beans necessary for testing a Spring web controller.
Spring Boot provides @SpringBootTest annotation for integration testing. This annotation creates an application context and loads full application context.

