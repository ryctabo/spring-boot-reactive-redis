## BUILDER STAGE
# More information: https://hub.docker.com/_/gradle
FROM gradle:6.8.2-jdk11 AS builder
# Establish workspace to build the console application
WORKDIR /spring-boot-reactive-redis-app
# Copy the gradle and sources files
COPY build.gradle .
COPY settings.gradle .
COPY src ./src
# Run the gradle command to build the console application
RUN gradle assemble

## RUNNER STAGE
# More information: https://hub.docker.com/_/openjdk
FROM openjdk:11-jre-slim
# Establish the workspace to run the application
WORKDIR /opt/spring-reactive-redis-app
# Copy the java application to run
COPY --from=builder /spring-boot-reactive-redis-app/build/libs/*.jar app.jar
# Run the console application
ENTRYPOINT ["java", "-jar", "app.jar"]