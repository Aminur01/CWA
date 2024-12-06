FROM openjdk:17-alpine
EXPOSE 8081
ADD target/cwaapplication.jar cwaapplication.jar
ENTRYPOINT ["java", "-jar", "/cwaapplication.jar"]
