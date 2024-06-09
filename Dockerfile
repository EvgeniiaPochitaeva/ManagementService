FROM eclipse-temurin:17-jre
COPY target/ManagementService-0.0.1-SNAPSHOT.jar /application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/application.jar"]