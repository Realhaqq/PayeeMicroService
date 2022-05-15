FROM maven:3.6.3-openjdk-11 AS maven_build

FROM openjdk

EXPOSE 8182

ADD target/user-service.jar user-service.jar

ENTRYPOINT ["java", "-jar", "user-service.jar"]
