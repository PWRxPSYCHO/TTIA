FROM amazoncorretto:17-alpine-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml ./

# Copy the rest of the project files to the container
COPY src/ ./src/

# Install Maven
RUN apk add maven

# Run the Maven build to compile and package the application
RUN mvn package -DskipTests

# Copy the generated jar file to the app directory
RUN cp target/*.jar app.jar

ENV PORT 5000
EXPOSE $PORT
# Start the application using the java command
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=${PROFILE}" ]