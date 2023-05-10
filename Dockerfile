FROM openjdk:11
ADD target/myproject.jar myproject.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "myproject.jar"]
