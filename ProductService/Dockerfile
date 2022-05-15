FROM maven:3.6.3-openjdk-11 AS maven_build

FROM openjdk

EXPOSE 8185

ADD target/wallet-service.jar wallet-service.jar

ENTRYPOINT ["java", "-jar", "wallet-service.jar"]
