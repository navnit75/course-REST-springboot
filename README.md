# Introduction
- Basic REST application, made with the help of `Springboot` and `Hibernate(JPA)` support. 
- To simulate a object relationship behaviour , all operations are based on entity `Course`. 

# APIs
|Method|API urls|Operation|
|------|--------|---------|
| GET  |/courses|Get all course|
| GET  |/courses/{courseId}|Get info regarding single Course|
| POST |/courses|Add new course|
| PUT  |/courses|Update the Course|
| DELETE|/courses/{courseId}|Delete the course Id|

# Run Commands
```bash
mvn clean && mvn compile
mvn spring-boot:run
mvn test
```
