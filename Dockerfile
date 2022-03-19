#Stage Build
FROM maven:3.8.4-openjdk-8-slim AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

#Stage Deploy
FROM openjdk:8-jre-slim-buster
COPY --from=builder /app/target/*.jar ./
ENTRYPOINT java -jar *.jar