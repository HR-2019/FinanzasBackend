FROM openjdk:8-slim
EXPOSE 8080
ADD ./target/finanzasbackend-docker.jar finanzasbackend-docker.jar
ENTRYPOINT ["java", "-jar", "/finanzasbackend-docker.jar"]