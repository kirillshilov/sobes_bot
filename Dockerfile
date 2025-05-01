FROM openjdk:17-jdk-slim
COPY target/sobes_bot-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]