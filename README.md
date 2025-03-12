# Spring Data JPA Query By Example and Specification
Demonstrate query by example(QBE) with Specification feature of Spring data JPA. 

# Technologies
- JDK 17
- Maven
- Spring Boot 3
- Spring Boot data JPA
- Lombok
- H2


# How to run
- Run the application as SpringBoot application
- While application starts up
  - **schema.sql** will be used to create table **TBL_USR** in H2 database
  - Table will be populated using **data-h2.sql** insert commands 

# How to access H2 console
1. Access h2-console using url
   - http://localhost:8080/h2-console
2. Provide userid and pwd as specified in following application properties
   - spring.datasource.username
   - spring.datasource.password
3. Check service startup logs. H2 Database should be available at datasource
   - jdbc:h2:mem:testdb

# API (s)
1. List all users
    - http://localhost:8080/user/list
2. Get all users having first name ends with 
    - http://localhost:8080/user/firstname?endsWith=ab
3. Get all users with last name contains
    - http://localhost:8080/user/lastname?contains=ga
4. Get all users with first name
    - http://localhost:8080/user/preeti
5. Get all disabled users whose email contains string and were disabled within time period
   - http://localhost:8080/user/disabled?emailContains=andh&fromDate=2025-02-02&toDate=2025-03-02