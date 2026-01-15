FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/patient-service-1.0.0.jar app.jar

EXPOSE 8081

ENV JAVA_OPTS="-Xmx512m -Xms256m"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

HEALTHCHECK --interval=30s --timeout=3s \
  CMD wget -qO- http://localhost:8081/actuator/health || exit 1