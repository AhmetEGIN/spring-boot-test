As the name suggests, integration tests focus on integrating different different layers of the application. That also means no mocking is involved.
Basically, we write integration tests for testing a feature which may involve interaction with multiple components.

**Examples:**

***Employee Management Feature:*** EmployeeRepository, EmployeeService, EmployeeController

***User Management Feature:*** UserRepository, UserService, UserController

***Login Feature:*** LoginRepository, LoginService, LoginController
 
---
## @SpringBootTest Annotation
* Spring Boot provides the @SpringBootTest annotation for Integration testing. This annotation creates an application context and loads full application context.
* @SpringBootTest will bootstrap the full application context, which means we can @Autowire any bean that's picked up by component scanning into our test.

* It starts the embedded server, creates a web environment and then enables @Test methods to do integration testing.
* By default, @SpringBootTest does not start the server. We need to add attribute webEnvironment to further refine how your tests run. It has several options:
  * **MOCK(Default)**: Loads a WebApplicationContext and provides a mock environment.
  * **RANDOM_PORT**: Loads a WebApplicationContext and provides a real web environment. The embedded server is started and listen on a random port. This is the one should be used for the integration test.
  * **DEFINED_PORT**: Loads a WebApplicationContext and provides a real web environment.
  * **NONE**: Loads an ApplicationContext but does not provide any web environment.





