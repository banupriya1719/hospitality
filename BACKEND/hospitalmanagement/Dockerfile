#
# Build stage
#
FROM maven:3.8.1-openjdk-11 AS build
COPY . .
RUN mvn clean install

#
# Package stage
#
FROM eclipse-temurin:11-jdk
COPY --from=build /target/hospitalmanagement-0.0.1-SNAPSHOT.jar demo.war
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-war","demo.war"]