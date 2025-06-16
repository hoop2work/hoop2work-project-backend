# Use OpenJDK 17 slim image
FROM openjdk:17-jdk-slim

# Install curl (needed for wait-for-it script)
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy jar file to container
COPY target/hoop2work-project-backend-0.0.1-SNAPSHOT.jar app.jar

# Add wait-for-it script to wait for MySQL
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

EXPOSE 4000

# Wait for MySQL to be available, then start the app
ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "--", "java", "-jar", "app.jar"]
