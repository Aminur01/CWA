FROM openjdk:17-alpine
EXPOSE 8081
ADD target/CwaApplication.jar CwaApplication.jar
ENTRYPOINT ["java", "-jar", "/CwaApplication.jar"]
