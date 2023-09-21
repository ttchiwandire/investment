# Momentum Investment Management Service

Follow the following Steps to start up the application:

1. Create a database with the database name momentum and default/public schema
2. The username for the database must be postgres and password must be p@$$word as configured in the application.yml
3. Run mvn command mvn clean install to build the application(First time you must disable flyway in the application.yml so that DB tables get created first)
4. Start the application
5. When the application start all database tables will be created and flyways scripts executed to populate the database
6. For the API documentation go to http://localhost:8080/momentum-invest/swagger-ui/index.html
7. Under the resources folder there's a postman collection that you can use to test all the endpoints

## Tools Used

| Tool               | Purpose                          | 
|--------------------|----------------------------------|
| Intellij           | IDE                              |
| Maven              | Build Tool                       |
| Git                | Version control system           |
| Java 17            | Java version used                |
| Mapstruct          | Mapping/conversion of DTOs       |
| Logstash           | Logging                          |
| Flyway             | DB SQL Scripts Migration Tool    |
| Postgres           | Database Engine                  |
 | JPA/JPL            | ORM Tool                         |
| Lombok             | Reduce boilerplate code          |
| SpringDoc Open API | API Documentation                |
| JUNIT Jupiter      | Unit testing                     |
| Mockito            | Mocking framework for unit tests |
| Jacoco             | Test coverage checks             |

In the event that you get any errors on the flyway scripts try to disable flyway and run the application first so that tables get created before  insert scripts are executed
