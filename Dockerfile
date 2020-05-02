FROM maven:3.6-jdk-8-alpine AS mvnimg
LABEL maintainer="edivaldorsj@gmail.com"
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package

FROM openjdk:8-jre-alpine
COPY --from=mvnimg /app/target/exemplo-spring-security-1.0.0.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]