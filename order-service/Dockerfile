# Use a base image with Java and Maven for building
FROM adoptopenjdk:11-jdk-hotspot as build
WORKDIR /app
COPY order-service/pom.xml .
RUN mvn clean install -DskipTests
COPY order-service/src/ ./src/
# Install Maven
RUN apt-get update && \
    apt-get install -y maven
RUN mvn package -DskipTests

# Create the final image with a minimal runtime environment
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/order-service.jar ./order-service.jar
EXPOSE 8088
CMD ["java", "-jar", "order-service.jar"]
