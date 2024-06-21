FROM maven:3.8.5-openjdk-17 AS builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM bellsoft/liberica-openjdk-alpine:17
ARG JAR_NAME="hw17-docker.jar"
COPY --from=builder /usr/src/app/target/$JAR_NAME /usr/app/$JAR_NAME
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/app/hw17-docker.jar"]
