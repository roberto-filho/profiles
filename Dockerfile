FROM openjdk:8u201-jdk-alpine as builder

COPY . /app
WORKDIR /app
RUN ./gradlew build -x test --no-daemon

FROM openjdk:8u201-jre-alpine
EXPOSE 8081
COPY --from=builder /app/build/libs/profiles.jar /app/
WORKDIR /app
CMD java -jar profiles.jar