### STAGE 1: Build ###
# jdk8 has a bug. Ref: https://bugs.openjdk.java.net/browse/JDK-8067747
FROM maven:3.6.3-openjdk-8-slim as builder

COPY . /sns-user
WORKDIR /sns-user
RUN mvn clean package -Dmaven.test.skip=true

### STAGE 2: Setup ###
FROM openjdk:8-jre-slim

COPY --from=builder /sns-user/target/sns-user*.jar /sns-user/sns-user.jar
WORKDIR /sns-user

EXPOSE 10080

CMD ["java", "-jar", "/sns-user/sns-user.jar"]
