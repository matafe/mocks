# Mocks Sample Project

## Some examples of unit test using.

- Simple unit test with JUnit 4.12 and Hamcrest 1.3;
- Simple unit test using stub object;
- Simple unit test using Mockito 1.10.19;
- Simple unit test using JMockit 1.39;
- Coverage using Jacoco plugin 0.8.1;

Notes:

In `src/main/java`:
The package `com.matafe.mocks.business` contains the Interfaces, Pojos and Exceptions.
The package `com.matafe.mocks.business.internal` contains the respective implementations.

In `src/main/test`:
The package `com.matafe.mocks.business.internal` contains the unit tests.

Unit test in a real world scenario, when a data base connection is need:
- `com.matafe.mocks.business.internal.MyDomainServiceWithRealTest`

Unit test with Stub object:
- `com.matafe.mocks.business.internal.MyDomainServiceWithDummyTest`

Unit test with Mockito:
- `com.matafe.mocks.business.internal.MyDomainServiceMockitoTest`

Unit test with JMockit object:
- `com.matafe.mocks.business.internal.MyDomainServiceJMockitTest`
- `com.matafe.mocks.business.internal.SequenceGeneratorTest`

Dev Notes
- JDK9+ use -Djdk.attach.allowAttachSelf
- All testing are compiling with JDK 10 (class version 54.0) but they are all tested in previous versions for compatibility mode;
    - 1.7 (class version 51.0)
    - 1.8 (class version 52.0)
    - 1.9 (class version 53.0)
    - 10  (class version 54.0)

## Running the tests

> mvn clean test

## Coverage and view the reports

View the coverage (by jacoco) report in `target/jacoco-ut`


