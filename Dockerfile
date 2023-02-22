FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> jar
ARG JAR_FILE=target/openweather-spring-boot-1.0.0.OPENWEATHER.jar

MAINTAINER claudiu_cercel

# cd /opt/app
WORKDIR /opt/app

# cp spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} openweather-spring-boot.jar

# java -jar /opt/app/openweather-spring-boot.jar
ENTRYPOINT ["java","-jar","openweather-spring-boot.jar"]