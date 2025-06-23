FROM maven AS build
WORKDIR /app
COPY backend .
RUN mvn clean install

# Bruk en smalere base for runtime
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]