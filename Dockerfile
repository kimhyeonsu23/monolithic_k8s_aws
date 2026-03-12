# build stage (JDK + gradle) -> jar
FROM amazoncorretto:21-alpine as builder
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

RUN chmod +x gradlew
RUN ./gradlew dependencies

COPY src src
RUN ./gradlew bootJar

# runtime stage (jre -> jar)
#FROM amazoncorretto:21-jre-alpine # 원래 jre로 해야 하지만 아마존에서는 jre 버전 없음?
FROM amazoncorretto:21-alpine
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]