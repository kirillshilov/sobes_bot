FROM openjdk:17-jdk-slim
COPY target/sobes_bot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]