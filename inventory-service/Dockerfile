#######################################
# Multi-Stage Docker Build
#######################################

# Stage 1 – Builder

# Set base image for builder stage
FROM openjdk:8-jre-slim as builder

# Create and set the working directory
WORKDIR app

# ARG for builder stage
ARG JAR_FILE=target/*.jar

# Copy the Spring Boot fat JAR into the working directory
COPY ${JAR_FILE} app.jar

# Call the Spring Boot layer tools to extract the layers:
# (dependencies, spring-boot-loader, snapshot-dependencies and application)
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 2

# Set a final base image
FROM openjdk:8-jre-slim

# Create and set the working directory
WORKDIR app

# Copy each layer directory into image
# Remember, each COPY command will create an image layer.
# Thus, if your dependencies are not changing,
# a new layer is not created.
COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./

# Set a startup command to run on a container
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
