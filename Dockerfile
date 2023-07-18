FROM eclipse-temurin:17-jdk-jammy
EXPOSE 80
ARG JAR_FILE=target/dahuaLife.jar
COPY ${JAR_FILE} .
CMD [ "java", "-jar",  "/dahuaLife.jar"]
