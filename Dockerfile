# Use the official Maven image as the base image
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /monolithic-spring-boot
# Copy the project's pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline
# Copy the application source code
COPY src ./src
# Build the application
RUN mvn package -DskipTests -P default

# Create a separate image for the runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /monolithic-spring-boot
COPY --from=build /monolithic-spring-boot/target/monolithic-spring-boot-0.0.1.jar ./monolithic-spring-boot.jar
ENTRYPOINT ["java", "-jar", "monolithic-spring-boot.jar"]
