FROM maven:3.6.3-openjdk-11 AS maven_build

FROM openjdk

EXPOSE 8080

ADD target/GatewayService.jar GatewayService.jar

ENTRYPOINT ["java", "-jar", "GatewayService.jar"]
