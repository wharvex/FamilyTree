FROM gradle:8.7-jdk21 AS build
WORKDIR /app

# Cache dependency resolution layer
COPY build.gradle settings.gradle ./
RUN gradle dependencies --no-daemon -q 2>/dev/null || true

COPY src ./src
RUN gradle quarkusBuild --no-daemon -q

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=build /app/build/quarkus-app/lib/ /app/lib/
COPY --from=build /app/build/quarkus-app/*.jar /app/
COPY --from=build /app/build/quarkus-app/app/ /app/app/
COPY --from=build /app/build/quarkus-app/quarkus/ /app/quarkus/

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/quarkus-run.jar"]
