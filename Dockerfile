FROM openjdk:24-jdk
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005","-jar","app.jar"]

