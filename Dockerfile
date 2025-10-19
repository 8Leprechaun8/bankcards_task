FROM openjdk:17-jdk-alpine
COPY ./target/bankcards-1.0-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "bankcards-1.0-SNAPSHOT.jar"]