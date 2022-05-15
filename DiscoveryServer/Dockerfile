FROM maven:3.6.3-openjdk-11 AS maven_build

FROM openjdk

EXPOSE 8761

ADD target/payee-0.0.1-SNAPSHOT.jar payee-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "payee-0.0.1-SNAPSHOT.jar"]
