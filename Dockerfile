FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/*.jar

# copy and rename from the local machine to the container
COPY ${JAR_FILE} app.jar

# The command the Docker image is going to run: command, Param1, Param2
ENTRYPOINT ["java","-jar","/app.jar"]