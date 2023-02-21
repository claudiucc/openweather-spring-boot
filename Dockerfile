FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> jar
ARG JAR_FILE=target/*.jar

MAINTAINER claudiu_cercel

# cd /opt/app
WORKDIR /opt/app

# cp spring-boot-web.jar /opt/app/spring-boot-web.jar
COPY ${JAR_FILE} openweather-spring-boot.jar

# java -jar /opt/app/openweather-spring-boot.jar
ENTRYPOINT ["java","-jar","openweather-spring-boot.jar"]