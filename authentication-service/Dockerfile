# Use a base image with Java and Maven for building
FROM adoptopenjdk:11-jdk-hotspot as build
WORKDIR /app
COPY authentication-service/pom.xml .
RUN mvn clean install -DskipTests
COPY authentication-service/src/ ./src/
# Install Maven
RUN apt-get update && \
    apt-get install -y maven
RUN mvn package -DskipTests

# Create the final image with a minimal runtime environment
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/authentication-service.jar ./authentication-service.jar
EXPOSE 8080
CMD ["java", "-jar", "authentication-service.jar"]
