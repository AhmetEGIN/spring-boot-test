## assertTrue:
* The Assertions class provides the static assertTrue() method
* This method helps us validate that the value supplied to it is true.
* This method takes an actual value and checks whether it is true or not
* If the actual value is true, the test case will pass

## assertNull
* The Assertions class provides the static assertNull() method
* This method helps us validate that the actual object is null.
* This method takes an actual value and checks whether it is null
* If the actual value is null, the test case will pass

## assertNotNull
* The Assertions class provides the static assertNotNull() method
* This method helps us validate that the actual object is not null.
* This method takes an actual value and checks whether it is not null
* If the actual value is null, the test case will fail

## assertEquals
* The Assertions class provides the static assertEquals() method
* This method is used to verify that two values are equal (actual value and expected value)
* If the actual value is equal to the expected value, the test case will pass
* If the actual value is not equal to the expected value, the test case will fail

## assertNotEquals
* The Assertions class provides the static assertNotEquals() method
* This method is used to verify that two values are not equal (actual value and expected value)
* If the actual value is not equal to the expected value, the test case will pass
* If the actual value is  equal to the expected value, the test case will fail

## assertArrayEquals
* The Assertions class provides the static assertArrayEquals() method
* This method is used to verify that two arrays are equal (actual array and expected array)
* Arrays equality:
  * Each element of an array is equal
  * The order of elements should match
  * The number of elements should match
* If the actual array is equal to the expected array, the test case will pass
* If the actual array is not equal to the expected array, the test case will fail

## assertIterableEquals
* The assertIterableEquals method in JUnit is an assertion method used to verify that two iterables are deeply equal.
* Iterable equality:
  * Elements of Iterable are equal
  * The order of elements should match
  * The number of elements should match
* If the actual and expected iterables are deeply equal, the test case will pass
* If the actual and expected iterables are not deeply equal, the test case will fail

## assertThrows
* The assertThrows method in JUnit is used to assert that a block of code throws a specific type of exception
* If the code does not throw the expected exception, the assertion fails, and the test is marked as failed
* If the code throws the expected exception, the assertion passes, and the test is marked as passed
* It follows an inheritance hierarchy, the assertion will pass if the expected type is the superclass (parent) of the actual exception type (subclass)

## assertThrowsExactly
* The assertThrowsExactly method in JUnit is used to assert that a block of code throws exactly a specific type of exception


---
## JUnit Display Test Names - @DisplayName
* The @DisplayName annotation is used to define a custom name for a test method or a test class
* This annotation helps provide descriptive names that can improve the readability of test results.
* By default, the test class name and test method names get printed when the test case is executed
* @DisplayName annotation takes strings with spaces between words, special characters, and emojis as well

## Disable Tests - @Disabled
* Disabling tests allows you to prevent specific test methods or classes from being executed during a test run
* The @Disabled annotation is used to disable a test method or a test class - JUnit will skip the execution of the @Disable annotated test methods or all test methods within the @Disabled annotated class

---
# JUnit Lifecycle Annotations
JUnit provides annotations to define test execution lifecycle  methods. These annotations help set up and clean up resources for tests

## @BeforeEach Annotation
* The @BeforeEach annotation marks a method that should be executed before each test method
* This is useful for setting up the test environment, such as initializing objects and setting initial conditions
* Avoid the duplicate code
* Ensures a clean state for every test method

## @AfterEach Annotation
* The @AfterEach annotation marks method that should be executed after each test method
* This is useful for cleaning up the test environment, such as resetting variables or closing connections
* It helps maintain a clean state between tests.

## @BeforeAll Annotation
* The @BeforeAll annotation marks method should be executed once before all test methods in the test class
* This is useful for setting up shared resources, such as establishing a database connection or starting a server
* @BeforeAll annotated method must be static
* It is ideal for expensive setups that don't need to be repeated

## @AfterAll Annotation
* The @AfterAll annotation marks method that should be executed once after all test methods in the test class
* This is useful for cleaning up shared resources, such as closing a database connection or shutting down a server
* @AfterAll annotated method must be static
* Ensures clean-up after all tests have run

---
 ## @RepeatedTest Annotation
* The @RepeatedTest annotation is used to indicate that a test method should be executed multiple times
* You can specify the number of repetitions as an argument to the annotation. ***@RepeatedTest(5)***
* Useful: Verifying that a method produces consistent results across multiple executions
* Useful: Stress testing a method to ensure it handles repeated calls correctly

## JUnit Nested Tests - @Nested Annotation
* Nested tests are a way to structure your tests hierarchically by nesting test classes within each other
* Nested tests can help to:
  * Organize tests that share a common setup
  * Group related tests logically
  * Provide a clear structure for complex test scenarios
* JUnit5 provides the @Nested annotation to create nested test classes

---
# JUnit Test Execution Order
* Test execution order refers to the sequence in which test methods are executed within a test class
* Ordering Tests can help ensure that dependent tests run in the required sequence and can help in debugging by consistently reproducing test failures
* By default, JUnit executes tests in an unpredictable order, which is often desirable to ensure that tests are independent of each other
* JUnit 5 provides the @TestMethodOrder and @Order annotations to control the order of test method execution














