
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY backend .
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests

CMD ["java", "-jar", "target/*.jar"]
