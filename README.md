# RoomOccupancyManager

## Circle CI Status: 
[![CircleCI](https://circleci.com/gh/MaMoreo/room-occupancy-manager.svg?style=svg)](https://circleci.com/gh/MaMoreo/room-occupancy-manager) 
![GitHub](https://img.shields.io/github/last-commit/MaMoreo/room-occupancy-manager)

A room occupancy optimization tool for hotels.

# Features
    • A working algorithm implemented in Java 11
    • Progress trackered through Git commits
    • Minimal readme explaining how to run the project and tests
    • Tests/TDD
    • Clean code structure and formatting

### Tech

Room Ocuppancy Manager uses a number of open source projects:

   - Spring Boot
  - RESTful API
  - In-Mem solution (No-DB)
  - JUnit 5 (Tests)
  - Maven (build tool)
  - Swagger (Automated API Documentation)
  - CircleCI (Continous Integration)
  - Docker


### Run the Tests

Tests can be run with Maven from a console

```sh
$ mvn clean test
```
## Run The application from a Console
To launch the project simply run the following command in a console.

```sh
$ mvn spring-boot:run
```

## Run the application with Docker
Docker will build the .jar with Maven and run the application

Build the image with Docker

```sh
$ docker build -t room-manager .
```
Create and run the container in background mapping the port 
```sh
$ docker run -d -p 8080:8080 room-manager
```
To stop the application
```sh
$ docker stop [CONTAINER_ID]
```
To enter in a running container
```sh
$ docker exec -it [CONTAINER_NAME] sh
```

## RESTfull API
Once the application is running point your browser to the following URL to access
the RESTfull API
> http://localhost:8080/swagger-ui/

From there the Room-Manager-Controller is available.

## Future Work:
* Unit Test the Controller using WebTestClient
* Add Integration Tests
* Validation of the input: **We rely on valid input from the user**.


## FEEDBACK: 

What was good:
- Minimal README
- A brief explanation about features / tech-stack and future work in README.
- Used GitHub issues 
- TDD
- Circle CI
- Lombok
- Swagger
- Clean project structure

What was not so good:
- Redundant object creations.
- Inconsistent code style

## Commit Message Format
Check the wiki page for Git commits messages format.

