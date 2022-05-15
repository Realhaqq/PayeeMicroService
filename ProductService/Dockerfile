FROM maven:3.6.3-openjdk-11 AS maven_build

FROM openjdk

EXPOSE 8081

ADD target/product-service.jar product-service.jar

ENTRYPOINT ["java", "-jar", "product-service.jar"]
