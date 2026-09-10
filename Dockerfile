# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src ./src
# Ensure the mvnw script is executable
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application using Tomcat 10 (which supports Jakarta EE 10)
FROM tomcat:10.1-jdk17
# Remove the default ROOT app
RUN rm -rf /usr/local/tomcat/webapps/ROOT
# Copy the compiled war file as the new ROOT app
# (Update the target name if pom.xml defines a different <finalName>, but usually it's artifactId-version)
COPY --from=build /app/target/unitrs-1.0.0.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
