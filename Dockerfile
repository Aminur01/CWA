FROM openjdk:17-alpine
EXPOSE 8081
ADD target/CwaApplication.jar cwaApplication.jar
ENTRYPOINT ["java", "-jar", "/cwaApplication.jar"]
