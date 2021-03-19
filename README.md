## Spring Boot Reference (Spring Boot REST API with MongoDB)

### About

Spring-Boot-Reference service is a Proof of concept (POC) created to demonstrate its feasibility as a realization to design and develop back-end of a website and a mobile app using Spring Boot REST APIs with MongoDB.

○ The service provides APIs to manage Enrollee. It includes Create, Retrieve, Update, Delete HTTP operations.

○ Service Resources are 

* POST (Create)     -> http://<hostname:port>/enrollees
* GET (Retrieve)    -> http://<hostname:port>/enrollees
* GET (Retrieve)    -> http://<hostname:port>/enrollees/{id}
* GET (Retrieve)    -> http://<hostname:port>/enrollees/persons/{id}
* GET (Retrieve)    -> http://<hostname:port>/enrollees?name={name}
* PUT (Update)      -> http://<hostname:port>/enrollees
* DELETE (Delete)   -> http://<hostname:port>/enrollees/{id}

### How it works

○ Prerequisite

    Java 8, Maven, Git
    
○ Clone the github Repository locally and checkout develop branch

    git clone https://github.com/aarav27/spring-boot-reference
    git branch -a
    git checkout develop
    
○ Build the spring-boot project

    mvn clean install

○ Run the spring-boot project with embedded mongodb

    mnv spring-boot:run

○ Run the spring-boot project with mongodb instance

    Feel free to reach me by email :
        - To make couple of changes in the checked-in code.
        - To install, run, create mongodb instance and database users.

### Author

○ **Aarav Sharma**

+ [github](https://github.com/aarav27)
+ [linkedin](https://www.linkedin.com/in/aaravsharma927/)



       
        
        
        




    


