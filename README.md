### Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
- For building and running the application you need:

    - [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
    - [Maven 3](https://maven.apache.org)
    - [PostgreSQL](https://www.postgresql.org)
    
- Refer to https://github.com/cumalikarakoc/database-ise-project repo to create the required database.

### Installation
- Run `mvn spring-boot:run` to start the application. 
- The application should be running by default on <http://localhost:8080>. You can change the port number in `src/main/resources/application.properties`. 
    - navigate to <http://localhost:8080/graphql/schema.json> for the description of GraphQL schema.

### Deployment
- Run `mvn clean install` to install dependencies and create required target files to be able to run the application. It will also create an executable .jar file, named as 'somerleyton-api.jar' which can be found in `graphQLApi-somerleyton/target` folder. You can run this .jar file in production.
- For more information on deployment, see the deployment diagram in the Technical Design for this project.
