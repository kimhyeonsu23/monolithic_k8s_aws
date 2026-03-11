# jdk image pull
# FROM eclipse-temurin:17-jdk-alpine
FROM amazoncorretto:21-alpine

# jar
ARG JAR_FILE=build/libs/*.jar

# copy
COPY ${JAR_FILE} ./backend.jar

# run
ENTRYPOINT ["java", "-jar", "./backend.jar"]