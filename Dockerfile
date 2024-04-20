FROM adoptopenjdk/openjdk17:alpine-jre

LABEL maintainer="mateo.rost@gmail.com"
LABEL version="0.0.1-SNAPSHOT"
LABEL description="Docker image for equipos-futbol Spring Boot application"

WORKDIR /app

COPY target/equipos-futbol-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
