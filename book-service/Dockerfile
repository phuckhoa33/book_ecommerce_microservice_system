# Use a base image with Java and Maven for building
FROM adoptopenjdk:11-jdk-hotspot as build
WORKDIR /app
COPY book-service/pom.xml .
RUN mvn clean install -DskipTests
COPY book-service/src/ ./src/
RUN mvn package -DskipTests

# Create the final image with a minimal runtime environment
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/book-service.jar ./book-service.jar
EXPOSE 8090
CMD ["java", "-jar", "book-service.jar"]
